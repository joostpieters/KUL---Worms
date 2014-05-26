package worms.model.expressions;

import worms.model.types.Type;


public class VariableAccess extends Expression{
	
	private String name;

	public VariableAccess(String name){
		this.name = name;
	}

	@Override
	public Type getValue() {
		return WormSelf.getWorm().getProgram().getVars().get(name);
	}

	@Override
	public String toString() {
		return "variableaccess("+name+")";
	}

	@Override
	public Expression clone() {
		return new VariableAccess(this.name);
	}

}
