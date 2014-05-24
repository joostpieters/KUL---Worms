package worms.model.expressions;

public class NullLit extends Expression{
	
	public NullLit(){
		//nothing
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public String toString() {
		return "null";
	}
	
}
