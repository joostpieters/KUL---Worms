package worms.model.expressions;

import worms.model.Food;

public class IsFood extends Expression{
	
	private Expression obj;
	
	public IsFood(Expression obj){
		this.obj = obj;	
	}

	@Override
	public BooleanLit getValue() {
		if(obj.getValue() instanceof Food){
			return new BooleanLit(true);
		}
		else{
			return new BooleanLit(false);
		}
	}

	@Override
	public String toString() {
		return "isfood "+obj.toString();
	}

}