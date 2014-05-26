package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;
import worms.model.types.DoubleSort;

public class GetY extends Expression {
	
	private Expression obj;

	public GetY(Expression obj){
		this.obj = obj;
	}

	@Override
	public DoubleSort getValue() {
		if(obj.getValue().getValue() instanceof Worm){
			return new DoubleSort((double) (((Worm) obj.getValue().getValue()).getY()));
		}
		else{
			return new DoubleSort((double) (((Food) obj.getValue().getValue()).getY()));
		}
	}

	@Override
	public String toString() {
		return "gety "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new GetY(this.obj);
	}

}