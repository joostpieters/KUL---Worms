package worms.model.expressions;

public class NotExp extends Expression {

	private Expression e;

	public NotExp(Expression e){
		this.e = e;
	}

	@Override
	public BooleanLit getValue() {
		if(!(Boolean)e.getValue()){
			return new BooleanLit(true);
		}
		else{
			return new BooleanLit(false);
		}
	}

	@Override
	public String toString() {
		return "!"+e.toString();
	}
	
}
