package worms.model.expressions;


public class VariableAccess extends Expression{
	
	private String name;

	public VariableAccess(String name){
		this.name = name;
	}

	@Override
	public Object getValue() {
		return WormSelf.getWorm().getProgram().getVars().get(name);
	}

	@Override
	public String toString() {
		return "variableaccess("+name+")";
	}

}
