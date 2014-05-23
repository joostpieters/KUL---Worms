package worms.model.expressions;

public class AndExp extends Expression{
	
	private Expression left, right;

	public AndExp(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanLit getValue() {
		if((Boolean) left.getValue() && (Boolean) right.getValue()){
			return new BooleanLit(true);
		}
		return new BooleanLit(false);
	}

	@Override
	public String toString() {
		return left.toString()+" && "+right.toString();
	}

	
}