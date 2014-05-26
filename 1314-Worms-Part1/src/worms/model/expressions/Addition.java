package worms.model.expressions;
import worms.model.types.DoubleSort;

public class Addition extends Expression{
	
	private final Expression left, right;

	public Addition(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return left.toString()+" + "+right.toString() ;
	}

	@Override
	public DoubleSort getValue() {
		return new DoubleSort((Double) left.getValue().getValue() + (Double) right.getValue().getValue());
	}

	@Override
	public Expression clone() {
		return new Addition(this.left, this.right);
	}
	

}
