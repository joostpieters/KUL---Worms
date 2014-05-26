package worms.model.expressions;
import worms.model.types.BooleanSort;

public class BiggerThan extends Expression {
	
	private Expression left, right;

	public BiggerThan(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanSort getValue() {
		if((Double) left.getValue().getValue()> (Double) right.getValue().getValue()){
			return new BooleanSort(true);
		}
		return new BooleanSort(false);
	}

	@Override
	public String toString() {
		return left.toString()+" > "+right.toString();
	}

	@Override
	public Expression clone() {
		return new BiggerThan(this.left, this.right);
	}
	
	

}
