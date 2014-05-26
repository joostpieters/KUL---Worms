package worms.model.expressions;

import worms.model.types.BooleanSort;

public class SmallerThan extends Expression {
	
	private Expression left, right;

	public SmallerThan(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanSort getValue() {
		if((double) left.getValue().getValue() < (double) right.getValue().getValue()){
			return new BooleanSort(true);
		}
		return new BooleanSort(false);
	}

	@Override
	public String toString() {
		return left.toString()+" < "+right.toString();
	}

	@Override
	public Expression clone() {
		return new SmallerThan(this.left, this.right);
	}
	
	

}
