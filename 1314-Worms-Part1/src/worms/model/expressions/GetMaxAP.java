package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleSort;


public class GetMaxAP extends Expression {
	
	private Expression obj;

	public GetMaxAP(Expression obj){
		this.obj = obj;
	}

	@Override
	public DoubleSort getValue() {
		if(obj.getValue().getValue() instanceof Worm){
			return new DoubleSort((double) (((Worm) obj.getValue().getValue()).getMaxActionPoints()));
		}
		else{
			return new DoubleSort(0.0);
		}
	}

	@Override
	public String toString() {
		return "getmaxap "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new GetMaxAP(this.obj);
	}

}