package worms.model.types;

public class BooleanSort extends Type{
	
	private Boolean bool;
	
	
	public BooleanSort(){
	}

	public BooleanSort(Boolean bool){
		setValue(bool);
	}

	
	public void setValue(Boolean bool){
		this.bool = bool;
	}
	
	public Boolean getValue(){
		return bool;
	}

}
