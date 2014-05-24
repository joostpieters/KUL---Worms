package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;

public class GetRadius extends Expression {
	
	private Expression obj;

	public GetRadius(Expression obj){
		this.obj = obj;
	}

	@Override
	public Object getValue() {
		if(obj.getValue() instanceof Worm){
			return new DoubleLit(((Worm) obj.getValue()).getRadius());
		}
		else{
			return new DoubleLit(((Food) obj.getValue()).getRadius());
		}
	}

	@Override
	public String toString() {
		return "getradius "+obj.toString();
	}

}