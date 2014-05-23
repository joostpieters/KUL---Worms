package worms.model.expressions;

import worms.model.types.DoubleSort;

public class DoubleLit extends Expression{
	
	private DoubleSort dbl;
	
	public DoubleLit(Double dbl){
		setValue(new DoubleSort(dbl));
	}
	
	public DoubleLit(){
		setValue(new DoubleSort(0.0));
	}

	public void setValue(DoubleSort dbl){
		this.dbl = dbl;
	}
	
	@Override
	public Object getValue() {
		return dbl;
	}

	@Override
	public String toString() {
		return dbl.toString();
	}

}
