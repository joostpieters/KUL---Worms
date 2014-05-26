package worms.model.expressions;

import worms.model.types.BooleanSort;

public class OrExp extends Expression{
	
	private Expression left, right;

	public OrExp(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanSort getValue() {
		if((boolean) left.getValue().getValue() || (boolean) right.getValue().getValue()){
			return new BooleanSort(true);
		}
		return new BooleanSort(false);
	}

	@Override
	public String toString() {
		return left.toString()+" || "+right.toString();
	}

	@Override
	public Expression clone() {
		return new OrExp(this.left, this.right);
	}

	
}
