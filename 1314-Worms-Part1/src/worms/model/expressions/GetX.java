package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;
import worms.model.types.DoubleSort;

public class GetX extends Expression {
	
	private Expression obj;

	public GetX(Expression obj){
		this.obj = obj;
	}

	@Override
	public DoubleSort getValue() {
		if(obj.getValue().getValue() instanceof Worm){
			return new DoubleSort((double) (((Worm) obj.getValue().getValue()).getX()));
		}
		else{
			return new DoubleSort((double) (((Food) obj.getValue().getValue()).getX()));
		}
	}

	@Override
	public String toString() {
		return "getx "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new GetX(this.obj);
	}

}
