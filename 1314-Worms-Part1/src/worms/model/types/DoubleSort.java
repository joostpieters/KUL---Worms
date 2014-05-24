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

	public void setValue(Object obj) {
		if(!(obj instanceof Double)){
			return;
		}
		this.dbl = (Double) dbl;
		
	}
}
