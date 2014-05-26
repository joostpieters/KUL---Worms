package worms.model.expressions;

import worms.model.types.DoubleSort;

public class Multiplication extends Expression{

	private Expression left, right;

	public Multiplication(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public DoubleSort getValue() {
		return new DoubleSort((double) left.getValue().getValue()*(double) right.getValue().getValue());
	}
	
	public String toString(){
		return left.toString()+"*"+right.toString();
	}

	@Override
	public Expression clone() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
