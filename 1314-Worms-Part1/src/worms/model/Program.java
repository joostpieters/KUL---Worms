package worms.model;

import java.util.Map;

import worms.gui.game.IActionHandler;
import worms.model.statements.Statement;
import worms.model.types.Type;

public class Program {
	
	private Statement statement;
	public IActionHandler handler;
	public Worm worm;
	private Map<String, Type> vars;
	
	public Program(Statement statement, IActionHandler handler, Map<String, Type> vars){
		setStatement(statement);
		setHandler(handler);
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
		this.worm = worm;
	}
	
	public Worm getWorm(){
		return worm;
	}
	
	public void execute(){
		try{
			statement.run();
		}
		catch(Error e){
			System.out.println("Program failed: "+e.getMessage());
		}
		if(!worm.isActive()){
			return;
		}
	}

	public void terminate() {
		// TODO Auto-generated method stub
		
	}
}
