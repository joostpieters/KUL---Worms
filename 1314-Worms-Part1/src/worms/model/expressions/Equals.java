package worms.model.expressions;

public class Equals extends Expression {
	
	private Expression left, right;

	public Equals(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}

	@Override
	public BooleanLit getValue() {
		if(left.getValue().equals(right.getValue())){
			return new BooleanLit(true);
		}
		return new BooleanLit(false);
	}

	@Override
	public String toString() {
		return left.toString()+" == "+right.toString();
	}

}