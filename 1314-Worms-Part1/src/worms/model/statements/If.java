package worms.model.statements;

import worms.model.expressions.Expression;

public class If extends Statement{
	
	private Expression cond;
	private Statement other;
	private Statement then;
	private boolean ran = false;

	public If(Expression cond, Statement then, Statement other){
		this.cond = cond;
		this.then = then;
		this.other = other;
	}

	@Override
	public String toString() {
		return "if ("+cond.toString()+") {"+then.toString()+"} else {"+other.toString()+"}";
	}

	@Override
	public void run() {
		ran = false;
		if((Boolean) cond.getValue()){
			then.run();
			ran = true;
		}
		else if(!(Boolean) cond.getValue()){
			other.run();
			ran = true;
		}
	}

	@Override
	public boolean isRan() {
		return ran;
	}
}
