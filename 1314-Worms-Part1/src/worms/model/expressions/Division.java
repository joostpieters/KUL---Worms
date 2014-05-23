package worms.model.expressions;

public class Division extends Expression{

	private Expression top, bottom;

	public Division(Expression top, Expression bottom){
		this.top = top;
		this.bottom = bottom;
	}
	
	@Override
	public DoubleLit getValue() {
		return new DoubleLit((Double) top.getValue()/(Double) bottom.getValue());
	}
	
	public String toString(){
		return top.toString()+"/"+bottom.toString();
	}

	
	
}
