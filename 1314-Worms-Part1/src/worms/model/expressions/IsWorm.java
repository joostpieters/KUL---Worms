package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.BooleanSort;

public class IsWorm extends Expression{
	
	private Expression obj;
	
	public IsWorm(Expression obj){
		this.obj = obj;	
	}

	@Override
	public BooleanSort getValue() {
		if(obj.getValue().getValue() instanceof Worm){
			return new BooleanSort(true);
		}
		else{
			return new BooleanSort(false);
		}
	}

	@Override
	public String toString() {
		return "isworm "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new IsWorm(this.obj);
	}

}
