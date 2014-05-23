package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.WormSelf;

public class Move extends Statement{
	
	private boolean ran;
	
	public Move(){
		//Nothing to declare officer :)
	}

	@Override
	public String toString() {
		return "move";
	}

	@Override
	public void run() {
		Worm worm = WormSelf.getWorm();
		IActionHandler handler = worm.getProgram().getHandler();
		handler.move(worm);
		ran = true;
	}

	@Override
	public boolean isRan() {
		return ran;
	}
	
	

}