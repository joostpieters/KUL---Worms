package worms.model.expressions;

import worms.model.Worm;


public class GetDirection extends Expression {
	
	private Expression obj;

	public GetDirection(Expression obj){
		this.obj = obj;
	}

	@Override
	public Object getValue() {
		if(obj.getValue() instanceof Worm){
			return new DoubleLit(((Worm) obj.getValue()).getOrientation());
		}
		else{
			return new DoubleLit(0.0);
		}
	}

	@Override
	public String toString() {
		return "getdir "+obj.toString();
	}

}