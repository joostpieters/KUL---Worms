package worms.model;

import worms.gui.game.IActionHandler;

public class Program {
	
	private String rawInput;
	public IActionHandler handler;
	
	public Program(String textFile, IActionHandler handler){
		setText(textFile);
		setHandler(handler);
	}
	
	public void setText(String text){
		this.rawInput = text;
	}

	public String getText(){
		return rawInput;
	}
	
	public void setHandler(IActionHandler handler){
		this.handler = handler;
	}
}
