package worms.model.expressions;

import worms.model.Worm;


public class GetMaxAP extends Expression {
	
	private Expression obj;

	public GetMaxAP(Expression obj){
		this.obj = obj;
	}

	@Override
	public Object getValue() {
		if(obj.getValue() instanceof Worm){
			return new DoubleLit((double)(((Worm) obj.getValue()).getMaxActionPoints()));
		}
		else{
			return new DoubleLit(0.0);
		}
	}

	@Override
	public String toString() {
		return "getmaxap "+obj.toString();
	}

}