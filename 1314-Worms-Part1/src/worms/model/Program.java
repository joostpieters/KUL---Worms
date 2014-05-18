package worms.model;

import worms.gui.game.IActionHandler;

public class Program {
	
	private Statement statement;
	public IActionHandler handler;
	public Worm worm;
	
	public Program(Statement statement, IActionHandler handler){
		setStatement(statement);
		setHandler(handler);
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
		this.worm = worm;
	}
	
	public Worm getWorm(){
		return worm;
	}
	
	public void execute(){
		try{
			statement.execute(this);
		}
		catch(Error e){
			System.out.println("Program failed: "+e.getMessage());
		}
		if(!worm.isActive()){
			return;
		}
	}
}
