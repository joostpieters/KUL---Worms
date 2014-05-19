package worms.model.expressions;

import worms.model.types.Type;

public class Addition extends Expression{
	
	private final Expression left, right;

	public Addition(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return left+" + "+right+" = " ;
	}

	@Override
	public Type getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
