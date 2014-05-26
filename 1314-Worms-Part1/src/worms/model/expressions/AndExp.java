package worms.model.expressions;

import worms.model.types.BooleanSort;

public class AndExp extends Expression{
	
	private Expression left, right;

	public AndExp(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanSort getValue() {
		BooleanSort and = new BooleanSort((boolean) left.getValue().getValue() && (boolean) right.getValue().getValue());
		return and;
	}

	@Override
	public String toString() {
		return left.toString()+" && "+right.toString();
	}

	@Override
	public Expression clone() {
		return new AndExp(this.left, this.right);
	}

	
}