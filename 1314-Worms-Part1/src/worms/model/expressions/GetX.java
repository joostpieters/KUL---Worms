package worms.model.expressions;

import worms.model.Food;
import worms.model.Worm;

public class GetX extends Expression {
	
	private Expression obj;

	public GetX(Expression obj){
		this.obj = obj;
	}

	@Override
	public Object getValue() {
		if(obj.getValue() instanceof Worm){
			return new DoubleLit(((Worm) obj.getValue()).getX());
		}
		else{
			return new DoubleLit(((Food) obj.getValue()).getX());
		}
	}

	@Override
	public String toString() {
		return "getx "+obj.toString();
	}

}
