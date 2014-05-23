package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.WormSelf;

public class ToggleWeapon extends Statement{
	
	private boolean ran;
	
	public ToggleWeapon(){
		//Nothing to declare officer :)
	}

	@Override
	public String toString() {
		return "toggleweap";
	}

	@Override
	public void run() {
		Worm worm = WormSelf.getWorm();
		IActionHandler handler = worm.getProgram().getHandler();
		handler.toggleWeapon(worm);
		ran = true;
	}

	@Override
	public boolean isRan() {
		return ran;
	}
	
	

}