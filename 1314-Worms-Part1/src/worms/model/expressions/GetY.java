package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;

public class GetY extends Expression {
	
	private Expression obj;

	public GetY(Expression obj){
		this.obj = obj;
	}

	@Override
	public Object getValue() {
		if(obj.getValue() instanceof Worm){
			return new DoubleLit(((Worm) obj.getValue()).getY());
		}
		else{
			return new DoubleLit(((Food) obj.getValue()).getY());
		}
	}

	@Override
	public String toString() {
		return "gety "+obj.toString();
	}

}