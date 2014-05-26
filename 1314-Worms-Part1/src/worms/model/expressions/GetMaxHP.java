package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleSort;


public class GetMaxHP extends Expression {
	
	private Expression obj;

	public GetMaxHP(Expression obj){
		this.obj = obj;
	}

	@Override
	public DoubleSort getValue() {
		if(obj.getValue().getValue() instanceof Worm){
			return new DoubleSort((double) (((Worm) obj.getValue().getValue()).getMaxHitPoints()));
		}
		else{
			return new DoubleSort(0.0);
		}
	}

	@Override
	public String toString() {
		return "getmaxhp "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new GetMaxHP(this.obj);
	}

}