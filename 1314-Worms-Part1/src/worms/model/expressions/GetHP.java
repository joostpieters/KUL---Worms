package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleSort;


public class GetHP extends Expression {
	
	private Expression obj;

	public GetHP(Expression obj){
		this.obj = obj;
	}

	@Override
	public DoubleSort getValue() {
		if(obj.getValue().getValue() instanceof Worm){
			return new DoubleSort((double) (((Worm) obj.getValue().getValue()).getHitPoints()));
		}
		else{
			return new DoubleSort(0.0);
		}
	}

	@Override
	public String toString() {
		return "gethp "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new GetHP(this.obj);
	}

}