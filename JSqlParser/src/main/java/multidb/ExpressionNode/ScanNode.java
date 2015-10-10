package multidb.ExpressionNode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class ScanNode extends AbstractNode {

    /**
     * 存储队列
     */
    BlockingQueue<JSONObject> basket = new LinkedBlockingQueue<>(3);


    /**
     * 构造函数
     * @param parent 节点
     */
    public ScanNode(AbstractNode parent) {
        super(parent);
    }

    /**
     * 返回
     * @return JSONArray
     */
    public JSONArray getFullTuple() {
        return null;
    }

    @Override
    public JSONObject getNextTuple() {
        // TODO Auto-generated method stub
        JSONObject obj;
        try {
            obj = basket.poll(60, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            return null;
            //e.printStackTrace();
        }
        return obj;
    }


}
