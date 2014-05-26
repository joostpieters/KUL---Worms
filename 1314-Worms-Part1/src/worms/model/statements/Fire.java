package worms.model.statements;

import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.expressions.Expression;
import worms.model.expressions.WormSelf;

public class Fire extends Statement{
	
	private Expression yield;
	private boolean ran = false;

	public Fire(Expression yield){
		this.yield = yield;
	}

	@Override
	public String toString() {
		return "fire "+yield.toString();
	}

	@Override
	public void run() {
		Worm worm = WormSelf.getWorm();
		IActionHandler handler = worm.getProgram().getHandler();
		handler.fire(worm, (int)yield.getValue().getValue());
		ran = true;
	}

	@Override
	public boolean isRan() {
		return ran;
	}

}
