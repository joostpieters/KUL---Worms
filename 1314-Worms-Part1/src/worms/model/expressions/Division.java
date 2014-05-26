package worms.model.expressions;
import worms.model.types.DoubleSort;

public class Division extends Expression{

	private Expression top, bottom;

	public Division(Expression top, Expression bottom){
		this.top = top;
		this.bottom = bottom;
	}
	
	@Override
	public DoubleSort getValue() {
		return new DoubleSort((Double) top.getValue().getValue()/(Double) bottom.getValue().getValue());
	}
	
	public String toString(){
		return top.toString()+"/"+bottom.toString();
	}

	@Override
	public Expression clone() {
		return new Division(this.top, this.bottom);
	}

	
	
}
