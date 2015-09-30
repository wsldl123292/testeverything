package multidb.metadata;

import java.util.List;

public class CustomFunction {

	String funName;
	String returnDataType;
	List<String> paraName;
	List<String> paraType;

	String script;

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getReturnDataType() {
		return returnDataType;
	}

	public void setReturnDataType(String returnDataType) {
		this.returnDataType = returnDataType;
	}

	public List<String> getParaName() {
		return paraName;
	}

	public void setParaName(List<String> paraName) {
		this.paraName = paraName;
	}

	public List<String> getParaType() {
		return paraType;
	}

	public void setParaType(List<String> paraType) {
		this.paraType = paraType;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}
	
}
