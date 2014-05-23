package worms.model.expressions;

public class BiggerOrEqual extends Expression {
	
	private Expression left, right;

	public BiggerOrEqual(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanLit getValue() {
		if((Double) left.getValue() >= (Double) right.getValue()){
			return new BooleanLit(true);
		}
		return new BooleanLit(false);
	}

	@Override
	public String toString() {
		return left.toString()+" >= "+right.toString();
	}
	
	

}