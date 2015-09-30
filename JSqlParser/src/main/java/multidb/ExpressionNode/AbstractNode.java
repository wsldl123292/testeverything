package multidb.ExpressionNode;

import com.alibaba.fastjson.JSONObject;

public abstract class AbstractNode {
    public enum EXESTATUS {
        EXE_ERROR, EXE_SUCCESS, EXE_TIMEOUT, EXE_PENDING
    }

    AbstractNode parent;

    public AbstractNode(AbstractNode parent) {
        this.parent = parent;
    }

    public AbstractNode() {

    }

    public void exec() {
    }

    public JSONObject getNextTuple() {
        return null;
    }

    public boolean isComplete() {
        return false;
    }

    public EXESTATUS getStatus() {
        return EXESTATUS.EXE_PENDING;
    }

    public void close() {

    }

    public void notify(AbstractNode node) {

    }
}
