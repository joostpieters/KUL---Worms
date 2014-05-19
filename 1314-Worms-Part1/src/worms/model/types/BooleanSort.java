package worms.model.types;

public class BooleanSort extends Type{
	
	private Boolean bool;

	public BooleanSort(Boolean bool){
		setValue(bool);
	}
	
	public BooleanSort(){
		setValue(false);
	}
	
	public void setValue(Object bool){
		if(!(bool instanceof Boolean )){
			return;
		}
		this.bool = (Boolean) bool;
	}
	
	public Boolean getValue(){
		return bool;
	}

}
