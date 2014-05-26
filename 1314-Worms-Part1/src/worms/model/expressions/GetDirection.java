package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleSort;


public class GetDirection extends Expression {
	
	private Expression obj;

	public GetDirection(Expression obj){
		this.obj = obj;
	}

	@Override
	public DoubleSort getValue() {
		if(obj.getValue().getValue() instanceof Worm){
			return new DoubleSort((double) (((Worm) obj.getValue().getValue()).getOrientation()));
		}
		else{
			return new DoubleSort(0.0);
		}
	}

	@Override
	public String toString() {
		return "getdir "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new GetDirection(this.obj);
	}

}