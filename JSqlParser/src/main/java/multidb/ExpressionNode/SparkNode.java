package multidb.ExpressionNode;

import com.alibaba.fastjson.JSONObject;
import com.saibowisdom.base.api.multidb.AbstractNode;
import com.saibowisdom.base.api.multidb.QueryCallback;
import jobserverclient.client.SparkJobServerClientException;
import jobserverclient.client.SparkJobServerClientFactory;
import jobserverclient.entity.SparkJobBaseInfo;
import jobserverclient.entity.SparkJobResult;
import jobserverclient.globle.ISparkJobServerClient;
import jobserverclient.globle.ISparkJobServerClientConstants;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SparkNode extends AbstractNode {

    private String sql;
    /**
     * 回调
     */
    QueryCallback callback;
     /**
     * 状态
     */
    EXESTATUS exestatus = EXESTATUS.EXE_PENDING;

    /**
     * 构造函数
     *
     * @param sql      sql
     * @param callback 回调函数
     */
    public SparkNode(final String sql, final QueryCallback callback) {
        this.sql = sql;
        this.callback = callback;

        final Thread thread = new Thread(new Runnable() {
            public void run() {
                runTask(sql);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


    @Override
    public JSONObject getNextTuple() {
        return null;
    }

    @Override
    public boolean isComplete() {
        // TODO Auto-generated method stub
        return super.isComplete();
    }

    @Override
    public EXESTATUS getStatus() {

        return exestatus;
    }

    @Override
    public void notify(AbstractNode node) {
        // TODO Auto-generated method stub
        super.notify(node);
    }

    /**
     * 执行函数
     *
     * @param sql 原始sql
     */
    public void runTask(String sql) {
        ISparkJobServerClient client = null;
        /** 对sql+当前时间进行md5加密最为存储的rdd名称 */
        final String rddName = md5(sql + System.currentTimeMillis());
        try {
            client = SparkJobServerClientFactory.getInstance().createSparkJobServerClient("http://192.168.1.212:8090/");
            /** 获取context 信息 */
            final List<String> contexts = client.getContexts();
            boolean flag = true;
            /** 不存在contexts */
            if (contexts.size() == 0) {
                final Map<String, String> params = new HashMap<>();
                params.put(ISparkJobServerClientConstants.PARAM_MEM_PER_NODE, "512m");
                params.put(ISparkJobServerClientConstants.PARAM_NUM_CPU_CORES, "1");
                flag = client.createContext("contexts", params);
            }

            if (flag) {
                /** 创建查询job 同步等待返回结果*/
                final Map<String, String> params = new HashMap<>();
                params.put(ISparkJobServerClientConstants.PARAM_APP_NAME, "selectResult");
                params.put(ISparkJobServerClientConstants.PARAM_CLASS_PATH, "GetResults");
                params.put(ISparkJobServerClientConstants.PARAM_CONTEXT, "contexts");
                params.put(ISparkJobServerClientConstants.PARAM_SYNC, "true");
                final SparkJobResult selectJobResult = client.startJob("input.string= fdsafd dfsf blullkfdsoflaw fsdfs", params);
                /** 执行成功 */
                if (SparkJobBaseInfo.INFO_STATUS_OK.equals(selectJobResult.getStatus())) {
                    /** 启动分析job */
                    params.clear();
                    params.put(ISparkJobServerClientConstants.PARAM_APP_NAME, "selectResult");
                    params.put(ISparkJobServerClientConstants.PARAM_CLASS_PATH, "GetResults");
                    params.put(ISparkJobServerClientConstants.PARAM_CONTEXT, "contexts");
                    params.put(ISparkJobServerClientConstants.PARAM_SYNC, "true");
                    final SparkJobResult analysisJobResult = client.startJob("input.string= fdsafd dfsf blullkfdsoflaw fsdfs", params);
                    if (SparkJobBaseInfo.INFO_STATUS_OK.equals(analysisJobResult.getStatus())) {
                        callback.callback("ddd",EXESTATUS.EXE_SUCCESS);
                    }
                }
            }
        } catch (SparkJobServerClientException e) {
            e.printStackTrace();
        }
    }


    /**
     * md5加密
     *
     * @param source 待加密
     * @return 加密后
     */
    public String md5(String source) {
        String des = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] result = md.digest(source.getBytes());
            StringBuilder buf = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                byte b = result[i];
                buf.append(String.format("%02X", b));
            }
            des = buf.toString().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("md5 failure");
        }
        return des;
    }
}
