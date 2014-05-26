package worms.model.statements;

import worms.model.expressions.Expression;

public class While extends Statement{
	
	private Expression cond;
	private Statement s;
	private boolean ran= false;

	public While(Expression cond, Statement s){
		this.cond = cond;
		this.s = s;
	}

	@Override
	public String toString() {
		return "while("+cond.toString()+") do {"+s.toString()+"}";
	}

	@Override
	public void run() {
		ran = false;
		while((boolean) (cond.getValue()).getValue()){
			s.run();
			ran = true;
		}
				
	}

	@Override
	public boolean isRan() {
		return ran;
	}
	
	

}
