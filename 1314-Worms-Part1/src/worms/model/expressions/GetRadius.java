package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;
import worms.model.types.DoubleSort;

public class GetRadius extends Expression {
	
	private Expression obj;

	public GetRadius(Expression obj){
		this.obj = obj;
	}

	@Override
	public DoubleSort getValue() {
		if(obj.getValue().getValue() instanceof Worm){
			return new DoubleSort((double) (((Worm) obj.getValue().getValue()).getRadius()));
		}
		else{
			return new DoubleSort((double) (((Food) obj.getValue().getValue()).getRadius()));
		}
	}

	@Override
	public String toString() {
		return "getradius "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new GetRadius(this.obj);
	}

}