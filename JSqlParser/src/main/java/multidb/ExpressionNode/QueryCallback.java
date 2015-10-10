package multidb.ExpressionNode;


public interface QueryCallback {

	void callback(String node, AbstractNode.EXESTATUS status);
}
