package worms.model.expressions;

import worms.model.Worm;

public class IsWorm extends Expression{
	
	private Expression obj;
	
	public IsWorm(Expression obj){
		this.obj = obj;	
	}

	@Override
	public BooleanLit getValue() {
		if(obj.getValue() instanceof Worm){
			return new BooleanLit(true);
		}
		else{
			return new BooleanLit(false);
		}
	}

	@Override
	public String toString() {
		return "isworm "+obj.toString();
	}

}
