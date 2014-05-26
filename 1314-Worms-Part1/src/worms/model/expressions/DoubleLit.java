package worms.model.expressions;

import worms.model.types.DoubleSort;

public class DoubleLit extends Expression{
	
	private DoubleSort val;
	private Double dbl;
	
	public DoubleLit(Double dbl){
		DoubleSort val = new DoubleSort(dbl);
		this.val = val;
		this.dbl = dbl;
	}
	
	public DoubleLit(){
		this.dbl = 0.0;
		DoubleSort val = new DoubleSort(dbl);
		this.val = val;
		}
	
	@Override
	public DoubleSort getValue() {
		return val;
	}

	@Override
	public String toString() {
		return dbl.toString();
	}

	@Override
	public Expression clone() {
		return new DoubleLit(this.dbl);
	}

}
