package multidb.ExpressionNode;


public interface QueryCallback {

	public void callback(AbstractNode node, AbstractNode.EXESTATUS status);
}
