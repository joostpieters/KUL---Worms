package worms.model.expressions;

import worms.model.types.DoubleSort;

public class NullLit extends Expression{
	
	public NullLit(){
		//nothing
	}

	@Override
	public DoubleSort getValue() {
		return null;
	}

	@Override
	public String toString() {
		return "null";
	}

	@Override
	public Expression clone() {
		return new NullLit();
	}
	
}
