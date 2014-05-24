package worms.model.statements;

import java.util.List;


public class Sequence extends Statement {
	
	private List<Statement> s;
	private boolean ran = false;
	private int i = 0;

	public Sequence(List<Statement> s){
		this.s = s;
	}

	@Override
	public String toString() {
		return "s*";
	}

	@Override
	public void run() {
		ran = false;
		if(i == s.size()){
			i = 0;
		}
		while(i < s.size()){
			s.get(i).run();
			i++;
		}
		ran = true;
	}

	@Override
	public boolean isRan() {
		return ran;
	}

}
