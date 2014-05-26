package worms.model.expressions;

import worms.model.Food;
import worms.model.types.BooleanSort;

public class IsFood extends Expression{
	
	private Expression obj;
	
	public IsFood(Expression obj){
		this.obj = obj;	
	}

	@Override
	public BooleanSort getValue() {
		if(obj.getValue().getValue() instanceof Food){
			return new BooleanSort(true);
		}
		else{
			return new BooleanSort(false);
		}
	}

	@Override
	public String toString() {
		return "isfood "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new IsFood(this.obj);
	}

}