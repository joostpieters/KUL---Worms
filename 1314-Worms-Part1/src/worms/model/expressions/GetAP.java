package worms.model.expressions;

import worms.model.Worm;


public class GetAP extends Expression {
	
	private Expression obj;

	public GetAP(Expression obj){
		this.obj = obj;
	}

	@Override
	public Object getValue() {
		if(obj.getValue() instanceof Worm){
			return new DoubleLit((double)(((Worm) obj.getValue()).getActionPoints()));
		}
		else{
			return new DoubleLit(0.0);
		}
	}

	@Override
	public String toString() {
		return "getap "+obj.toString();
	}

}