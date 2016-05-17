import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 功 能:
 * 创建人: LDL
 * 时 间:  2015/12/29 11:08
 */
public class DataMigration {

    static final Settings settings = ImmutableSettings.settingsBuilder()
            .put("client.transport.sniff", true).put("cluster.name", "es").build();

    final static Client srcClient = new TransportClient()
            .addTransportAddress(new InetSocketTransportAddress("192.168.1.211", 9300));

    final static Client targetClient = new TransportClient(settings)
            .addTransportAddress(new InetSocketTransportAddress("192.168.1.212", 9300))
            .addTransportAddress(new InetSocketTransportAddress("192.168.1.213", 9300));

    private static final String index = "cms_data_saibo";
    private static final String type = "info";

    public static void main(String[] args) throws IOException {

        final List<Map<String, Object>> sourceMaps = new ArrayList<>();
        /*final SearchRequestBuilder searchRequestBuilder = srcClient.prepareSearch(index)
                .setTypes(type)
                .setFrom(0)
                .setSize(756);
        final SearchResponse response = searchRequestBuilder
                .execute()
                .actionGet();

        final SearchHits searchHits = response.getHits();
        for (int i = 0; i < searchHits.getHits().length; i++) {
            final SearchHit searchHit = searchHits.getHits()[i];
            final Map<String, Object> map = searchHit.sourceAsMap();
            map.put("_id", searchHit.getId());
            sourceMaps.add(map);
        }*/


        final Map<String, Object> hashmap = searchEsByESql("select * from es.cms_data_saibo$info limit 0, 10");
        final Object obj = hashmap.get("list");
        final JSONArray jsonArray = JSON.parseArray(obj.toString());
        for (int i = 0; i < jsonArray.size(); i++) {
            sourceMaps.add((Map<String, Object>) JSON.parse(jsonArray.getString(i)));
        }
        System.out.println(sourceMaps);

        final BulkRequestBuilder bulkRequest = targetClient.prepareBulk();

        for (Map<String, Object> sourceMap : sourceMaps) {
            final XContentBuilder xContentBuilder = jsonBuilder()
                    .startObject();
            for (String key : sourceMap.keySet()) {
                xContentBuilder.field(key, sourceMap.get(key));
            }
            xContentBuilder.endObject();
            bulkRequest.add(targetClient.prepareIndex(index, type, sourceMap.get("_id").toString())
                    .setSource(xContentBuilder)
            );
        }

        final BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        System.out.println(bulkResponse.hasFailures());
    }


    /**
     * 通过接口查询sql
     *
     * @param sql sql
     * @throws UnsupportedEncodingException 异常
     */
    public static Map<String, Object> searchEsByESql(String sql) throws UnsupportedEncodingException {
        final HttpClient httpclient = new DefaultHttpClient();
        final Map<String, Object> map = new HashMap<>();
        final String url = "http://dubboservice.saibowisdom.com/services/search/selectjson";
        final HttpPost pmethod = new HttpPost(url); // 设置响应头信息
        pmethod.addHeader("Connection", "keep-alive");
        pmethod.addHeader("Accept", "*/*");
        pmethod.addHeader("Content-Type", "application/json; charset=UTF-8");
        pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
        pmethod.addHeader("Cache-Control", "max-age=0");
        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");

        final HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 1000 * 120);//设置超时时间
        HttpConnectionParams.setSoTimeout(httpParameters, 1000 * 120);
        pmethod.setParams(httpParameters);

        final StringEntity se = new StringEntity(sql, HTTP.UTF_8);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        pmethod.setEntity(se);
        final HttpResponse res;
        String jsonStr = "";
        try {
            //res = HttpUtil.getHttpClient().execute(pmethod);
            res = httpclient.execute(pmethod);
            if (res != null) {
                final HttpEntity entity = res.getEntity();
                jsonStr = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("info", e.getMessage());
            e.printStackTrace();
        } finally {
//			System.out.println("release connection");
            pmethod.releaseConnection();
        }
        if (null != jsonStr && !"".equals(jsonStr)) {
            final JSONObject object = JSON.parseObject(jsonStr);
            if (null != object.get("error")) {
                map.put("success", false);
                map.put("info", object.get("error").toString());
            } else {
                map.put("success", true);
                map.put("list", object.get("data"));
                map.put("total", object.get("total"));
                map.put("qTime", object.get("took"));
            }

        }
        return map;

    }
}
