package worms.model.expressions;

import worms.model.types.DoubleSort;

public class Subtraction extends Expression{
	
	private final Expression left, right;

	public Subtraction(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return left.toString()+" - "+right.toString() ;
	}

	@Override
	public DoubleSort getValue() {
		return new DoubleSort((Double)left.getValue().getValue()-(Double) right.getValue().getValue());
	}

	@Override
	public Expression clone() {
		return new Subtraction(this.left, this.right);
	}
	

}
