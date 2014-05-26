package worms.model.expressions;

import worms.model.types.BooleanSort;

public class SmallerOrEqual extends Expression {
	
	private Expression left, right;

	public SmallerOrEqual(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanSort getValue() {
		if((double) left.getValue().getValue() <= (double) right.getValue().getValue()){
			return new BooleanSort(true);
		}
		return new BooleanSort(false);
	}

	@Override
	public String toString() {
		return left.toString()+" <= "+right.toString();
	}

	@Override
	public Expression clone() {
		return new SmallerOrEqual(this.left, this.right);
	}
	
	

}