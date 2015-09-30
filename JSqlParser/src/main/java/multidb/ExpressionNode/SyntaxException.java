package multidb.ExpressionNode;

public class SyntaxException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg = new String();
	public SyntaxException(String error)
	{
		msg = error;
	}
}
