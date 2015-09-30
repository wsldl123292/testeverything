package multidb.ExpressionNode;

import com.alibaba.fastjson.JSONArray;

public abstract class ScanNode extends AbstractNode {

	String jsonData;
	public ScanNode(AbstractNode parent){
		super(parent);
	}

	public JSONArray getFullTuple(){
		return null;
		
	}
}
