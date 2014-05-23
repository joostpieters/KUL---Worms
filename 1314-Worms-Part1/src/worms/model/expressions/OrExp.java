package worms.model.expressions;

public class OrExp extends Expression{
	
	private Expression left, right;

	public OrExp(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanLit getValue() {
		if((Boolean) left.getValue() || (Boolean) right.getValue()){
			return new BooleanLit(true);
		}
		return new BooleanLit(false);
	}

	@Override
	public String toString() {
		return left.toString()+" || "+right.toString();
	}

	
}
