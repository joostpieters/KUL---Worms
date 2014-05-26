package worms.model.types;

public class BooleanSort extends Type{
	
	private Boolean bool;
	
	
	public BooleanSort(){
	}

	public BooleanSort(Boolean bool){
		this.bool = bool;
	}
	
	public Boolean getValue(){
		return bool;
	}

	@Override
	public BooleanSort clone() {
		return new BooleanSort(getValue());
	}

}
