package worms.model.expressions;

public class Sqrt extends Expression{
	
	private Expression exp;

	public Sqrt(Expression exp){
		this.exp = exp;
	}

	@Override
	public DoubleLit getValue() {
		return new DoubleLit(Math.sqrt((Double)exp.getValue());
	}

	@Override
	public String toString() {
		return "sqrt("+exp.toString()+")";
	}

	
	
}
