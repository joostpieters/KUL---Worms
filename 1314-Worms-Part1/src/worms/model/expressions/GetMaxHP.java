package worms.model.expressions;

import worms.model.Worm;


public class GetMaxHP extends Expression {
	
	private Expression obj;

	public GetMaxHP(Expression obj){
		this.obj = obj;
	}

	@Override
	public Object getValue() {
		if(obj.getValue() instanceof Worm){
			return new DoubleLit((double)(((Worm) obj.getValue()).getMaxHitPoints()));
		}
		else{
			return new DoubleLit(0.0);
		}
	}

	@Override
	public String toString() {
		return "getmaxhp "+obj.toString();
	}

}