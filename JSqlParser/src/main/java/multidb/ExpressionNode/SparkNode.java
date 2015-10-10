package multidb.ExpressionNode;

import com.alibaba.fastjson.JSONObject;
import com.saibowisdom.base.api.multidb.AbstractNode;
import com.saibowisdom.base.api.multidb.QueryCallback;
import jobserverclient.client.SparkJobServerClientException;
import jobserverclient.client.SparkJobServerClientFactory;
import jobserverclient.globle.ISparkJobServerClient;


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
     * 使换行符转为字节数组
     */
    String lineSeparator = System.getProperty("line.separator");
    /**
     * 换行
     */
    byte[] newLine = lineSeparator.getBytes();

    /**
     * 构造函数
     *
     * @param sql     sql
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
     */
    public void runTask(String sql) {
        ISparkJobServerClient client = null;
        try {
            client = SparkJobServerClientFactory.getInstance().createSparkJobServerClient("http://192.168.1.212:8090/");
        } catch (SparkJobServerClientException e) {
            e.printStackTrace();
        }
    }

}
