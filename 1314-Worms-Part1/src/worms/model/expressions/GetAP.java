package worms.model.expressions;

import worms.model.Worm;
import worms.model.types.DoubleSort;


public class GetAP extends Expression {
	
	private Expression obj;

	public GetAP(Expression obj){
		this.obj = obj;
	}

	@Override
	public DoubleSort getValue() {
		return new DoubleSort((double) (((Worm) obj.getValue().getValue()).getActionPoints()));
		
	}

	@Override
	public String toString() {
		return "getap "+obj.toString();
	}

	@Override
	public Expression clone() {
		return new GetAP(this.obj);
	}

}