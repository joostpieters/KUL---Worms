package worms.model.expressions;

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
	public DoubleLit getValue() {
		return new DoubleLit((Double)left.getValue()+(Double) right.getValue());
	}
	

}
