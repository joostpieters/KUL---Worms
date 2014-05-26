package worms.model.expressions;

import worms.model.types.BooleanSort;

public class NotExp extends Expression {

	private Expression e;

	public NotExp(Expression e){
		this.e = e;
	}

	@Override
	public BooleanSort getValue() {
		if(!(boolean)e.getValue().getValue()){
			return new BooleanSort(true);
		}
		else{
			return new BooleanSort(false);
		}
	}

	@Override
	public String toString() {
		return "!"+e.toString();
	}

	@Override
	public Expression clone() {
		return new NotExp(this.e);
	}
	
}
