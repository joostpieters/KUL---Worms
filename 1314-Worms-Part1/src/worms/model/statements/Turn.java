package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.Expression;
import worms.model.expressions.WormSelf;

public class Turn extends Statement{
	
	private Expression angle;
	private boolean ran = false;

	public Turn(Expression angle){
		this.angle = angle;
	}

	@Override
	public String toString() {
		return "turn "+angle.toString();
	}

	@Override
	public void run() {
		Worm worm = WormSelf.getWorm();
		IActionHandler handler = worm.getProgram().getHandler();
		handler.turn(worm, (int)angle.getValue().getValue());
		ran = true;
	}

	@Override
	public boolean isRan() {
		return ran;
	}

}