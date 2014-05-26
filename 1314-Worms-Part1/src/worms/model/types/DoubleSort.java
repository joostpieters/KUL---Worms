package worms.model.types;

public class DoubleSort extends Type {
	
	private double dbl;

	public DoubleSort(double dbl){
		setValue(dbl);
	}
	
	public DoubleSort(){
		setValue(0.0);
	}
	
	public Double getValue(){
		return dbl;
	}

	public void setValue(double dbl) {
		this.dbl = dbl;
		
	}

	@Override
	public Type clone() {
		return new DoubleSort(this.dbl);
	}
}
