package worms.model;

import java.util.List;
import java.util.Map;

import worms.gui.game.IActionHandler;
import worms.model.expressions.WormSelf;
import worms.model.statements.Statement;
import worms.model.types.Type;

/**
 * Class that creates a program object.
 * 
 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

public class Program {
	
	private Statement statement;
	public IActionHandler handler;
	public Worm worm;
	private Map<String, Type> vars;
	private List<Worm> wormslist;
	private boolean stopped;
	
	public Program(Statement statement, IActionHandler handler, Map<String, Type> vars){
		this.statement = statement;
		this.handler = handler;
		this.vars = vars;
	}
	
	public Map<String, Type> getVars(){
		return vars;
	}
	
	public void addVar(String name, Type e){
		getVars().put(name, e);
	}
	
	public void setStatement(Statement statement){
		this.statement = statement;
	}

	public Statement getStatement(){
		return statement;
	}
	
	public void setHandler(IActionHandler handler){
		this.handler = handler;
	}
	
	public IActionHandler getHandler(){
		return handler;
	}

	public void addWorm(Worm worm) {
		try{
			wormslist.add(worm);
		}
		catch(Exception e){
			
		}
	}
	
	public void removeWorm(Worm worm){
		wormslist.remove(worm);
	}
	
	public void execute(){
		stopped = false;
		try{
			statement.run();
			if(WormSelf.getWorm().getHitPoints() <= 0){
				World world = WormSelf.getWorm().getWorld();
				WormSelf.getWorm().remove();
				world.nextTurn();
			}
		}
		catch(Error e){
			System.out.println("Program failed: "+e.getMessage());
		}
		if(!worm.isActive()){
			return;
		}
	}

	public void terminate() {
		stopped = true;
	}
}
