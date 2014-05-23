package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.WormSelf;

public class Skip extends Statement{
	
	private boolean ran;
	
	public Skip(){
		//Nothing to declare officer :)
	}

	@Override
	public String toString() {
		return "skip";
	}

	@Override
	public void run() {
		Worm worm = WormSelf.getWorm();
		worm.getProgram().terminate();
		ran = true;
	}

	@Override
	public boolean isRan() {
		return ran;
	}
	
	

}