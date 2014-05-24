package worms.model.expressions;

import worms.model.Worm;


public class GetHP extends Expression {
	
	private Expression obj;

	public GetHP(Expression obj){
		this.obj = obj;
	}

	@Override
	public Object getValue() {
		if(obj.getValue() instanceof Worm){
			return new DoubleLit((double)(((Worm) obj.getValue()).getHitPoints()));
		}
		else{
			return new DoubleLit(0.0);
		}
	}

	@Override
	public String toString() {
		return "gethp "+obj.toString();
	}

}