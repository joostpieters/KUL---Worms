package worms.model.expressions;

import worms.model.types.BooleanSort;

public class BooleanLit extends Expression {
	
	private BooleanSort bool;
	
	public BooleanLit(){
		setValue(new BooleanSort(false));
	}
	
	public BooleanLit(Boolean bool){
		setValue(new BooleanSort(bool));
	}
	
	public void setValue(BooleanSort bool){
		this.bool = bool;
	}

	@Override
	public Object getValue() {
		return bool;
	}

	
}
