package worms.model.statements;

import worms.model.Worm;
import worms.model.expressions.Expression;
import worms.model.expressions.WormSelf;
import worms.model.types.Type;

public class Assign extends Statement{
	
	private String name;
	private boolean ran = false;
	private Expression e;

	public Assign(String name, Expression e){
		this.name = name;
		this.e = e;
	}

	@Override
	public String toString() {
		return name+" := "+e.toString();
	}

	@Override
	public void run() {
		Worm worm = WormSelf.getWorm();
		if(e.getValue() == null){
			worm.getProgram().addVar(name, null);
			ran = true;
		}
		else{
			worm.getProgram().addVar(name, (Type) e.getValue());
			ran = true;
		}
	}

	@Override
	public boolean isRan() {
		return ran;
	}

}
