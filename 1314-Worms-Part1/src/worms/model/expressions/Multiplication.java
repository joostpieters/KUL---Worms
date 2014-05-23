package worms.model.expressions;

public class Multiplication extends Expression{

	private Expression left, right;

	public Multiplication(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public DoubleLit getValue() {
		return new DoubleLit((Double) left.getValue()*(Double) right.getValue());
	}
	
	public String toString(){
		return left.toString()+"*"+right.toString();
	}

	
	
}
