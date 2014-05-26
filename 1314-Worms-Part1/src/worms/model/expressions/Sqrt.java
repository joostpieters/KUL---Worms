package worms.model.expressions;

import worms.model.types.DoubleSort;

public class Sqrt extends Expression{
	
	private Expression exp;

	public Sqrt(Expression exp){
		this.exp = exp;
	}

	@Override
	public DoubleSort getValue() {
		return new DoubleSort(Math.sqrt((double)exp.getValue().getValue()));
	}

	@Override
	public String toString() {
		return "sqrt("+exp.toString()+")";
	}

	@Override
	public Expression clone() {
		return new Sqrt(this.exp);
	}

	
	
}
