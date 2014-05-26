package worms.model.expressions;

import worms.model.types.BooleanSort;

public class NotEquals extends Expression {
	
	private Expression left, right;

	public NotEquals(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanSort getValue() {
		if(left.getValue().getValue() == null && right.getValue().getValue() == null){
			return new BooleanSort(false);
		}
		else{
			if(left.getValue().getValue().equals(right.getValue().getValue())){
			return new BooleanSort(false);
			}
		}
		return new BooleanSort(true);
	}

	@Override
	public String toString() {
		return left.toString()+" != "+right.toString();
	}

	@Override
	public Expression clone() {
		return new NotEquals(this.left, this.right);
	}

}
