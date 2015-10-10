package multidb.ExpressionNode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import multidb.EsUtil;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.util.concurrent.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ElasticSearchScanNode extends ScanNode {

    /**
     * sql
     */
    private String sql;

    /**
     * es客户端
     */
    private TransportClient transportClient;
    /**
     * 查询完成标识
     */
    private boolean isComplete;
    /**
     * 查询状态
     */
    private AbstractNode.EXESTATUS exestatus = AbstractNode.EXESTATUS.EXE_PENDING;
    /**
     * 查询返回存储
     */
    private ArrayList<String> list;

    /**
     * 结果map
     */
    private Map<String, Object> map = new LinkedHashMap<>();
    /**
     * es client
     */
    static final Client client = new TransportClient()
            //.addTransportAddress(new InetSocketTransportAddress("111.204.239.215", 9300));
            .addTransportAddress(new InetSocketTransportAddress("192.168.1.211", 9300));
    /**
     * 执行服务
     */
    final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    /**
     * 构造函数
     *
     * @param sql             sql
     * @param parent          parent
     * @param transportClient transportClient
     */
    public ElasticSearchScanNode(String sql, AbstractNode parent/*,TransportClient transportClient*/) {
        super(parent);
        this.sql = sql;
        this.transportClient = null;

    }

    @Override
    public void exec() {
        // TODO Auto-generated method stub
        final ListenableFuture<SearchHits> listenableFuture = executorService.submit(new Callable<SearchHits>() {
            @Override
            public SearchHits call() throws Exception {
                System.out.println(Thread.currentThread().getId() + "开始" + sql);
                final EsUtil esUtil = new EsUtil();
                final ActionRequestBuilder actionRequestBuilder = esUtil.convertSql(sql, client);
                final SearchResponse response = (SearchResponse) actionRequestBuilder
                        .execute().actionGet();

                /*map.put("took", response.getTookInMillis());
                map.put("total", response.getHits().getTotalHits());*/

                took = response.getTookInMillis();
                total = response.getHits().getTotalHits();
                return response.getHits();
            }
        });

        Futures.addCallback(listenableFuture, new FutureCallback<SearchHits>() {
            @Override
            public void onSuccess(SearchHits hits) {

                for (SearchHit hit : hits) {

                    final Map<String, Object> hitMap = hit.getSource();
                    /** 高亮 */
                    final Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    if (highlightFields != null) {
                        for (String key : highlightFields.keySet()) {
                            System.out.println(key);
                            final Text[] texts = highlightFields.get(key).getFragments();
                            hitMap.put(key, texts[0].toString().replace("\"", ""));
                        }
                    }


                    hitMap.put("_id", hit.getId());
                    hitMap.put("_score", hit.getScore());
                    JSONObject obj = JSON.parseObject(JSON.toJSONString(hitMap));
                    try {
                        basket.offer(obj, 60, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                try {
                    basket.put(new JSONObject());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + "完成");
                isComplete = true;
                exestatus = EXESTATUS.EXE_SUCCESS;
                executorService.shutdown();
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(Thread.currentThread().getId() + "失败");
                //list.add("{\"total\":\"" + throwable.getLocalizedMessage() + "\"}");
                //map.put("error", "sql to elasticsearch 发生错误，请联系管理员" + throwable.getLocalizedMessage());
                exestatus = EXESTATUS.EXE_ERROR;
                isComplete = true;
                executorService.shutdown();
            }
        });
    }


    @Override
    public boolean isComplete() {
        // TODO Auto-generated method stub
        return isComplete;
    }

    @Override
    public EXESTATUS getStatus() {
        // TODO Auto-generated method stub
        return exestatus;
    }

    @Override
    public JSONArray getFullTuple() {
        return JSON.parseArray(JSON.toJSONString(list));
    }

    /**
     * 返回全部查询
     *
     * @return json
     */
    public String getFullTupleToString() {
        return JSON.toJSONString(map);
    }
}
