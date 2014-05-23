package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.WormSelf;

public class Jump extends Statement{
	
	private boolean ran;
	
	public Jump(){
		//Nothing to declare officer :)
	}

	@Override
	public String toString() {
		return "jump";
	}

	@Override
	public void run() {
		Worm worm = WormSelf.getWorm();
		IActionHandler handler = worm.getProgram().getHandler();
		handler.jump(worm);
		ran = true;
	}

	@Override
	public boolean isRan() {
		return ran;
	}
	
	

}
