package worms.model.statements;

import worms.model.expressions.Expression;

public class Print extends Statement {
	
	private Expression toPrint;
	private boolean ran = false;

	public Print(Expression toPrint){
		this.toPrint = toPrint;
	}

	@Override
	public String toString() {
		return "print "+toPrint.toString();
	}

	@Override
	public void run() {
		if(toPrint.getValue() == null){
			System.out.println("null");
		}
		else{
		System.out.println(toPrint.getValue().toString());
		ran = true;
		}
	}

	@Override
	public boolean isRan() {
		return ran;
	}

}
