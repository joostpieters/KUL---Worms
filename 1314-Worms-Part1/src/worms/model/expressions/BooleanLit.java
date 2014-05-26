package worms.model.expressions;

import worms.model.types.BooleanSort;

public class BooleanLit extends Boolean {
	
	private BooleanSort val;
	private boolean bool;
	
	public BooleanLit(){
		this.bool = false;
		BooleanSort val = new BooleanSort(bool);
		this.val = val;	}
	
	public BooleanLit(boolean bool){
		this.bool = bool;
		BooleanSort val = new BooleanSort(bool);
		this.val = val;
	}

	@Override
	public BooleanSort getValue() {
		return val;
	}

	@Override
	public String toString() {
		if(this.bool == true){
			return "True";
		}
		else{
			return "False";
		}
	}

	@Override
	public BooleanLit clone() {
		return new BooleanLit(this.bool);
	}

	
}
