package multidb.ExpressionNode;

import com.alibaba.fastjson.JSONObject;


public class PhoenixScanNode extends ScanNode {
    @Override
    public JSONObject getNextTuple() {
        // TODO Auto-generated method stub
        return super.getNextTuple();
    }

    @Override
    public boolean isComplete() {
        // TODO Auto-generated method stub
        return super.isComplete();
    }

    @Override
    public EXESTATUS getStatus() {
        // TODO Auto-generated method stub
        return super.getStatus();
    }

    String sql;


    public PhoenixScanNode(String sql, AbstractNode parent) {
        super(parent);
        this.sql = sql;

    }

    @Override
    public void exec() {
        // TODO Auto-generated method stub

    }
}
