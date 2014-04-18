package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class IllegalNameException extends Exception {

	
	/**
	 * We'll first initialize, register and return
	 */
	
	private String name;
	
	public IllegalNameException(String name){
		this.name = name;
	}
	
	@Basic @Immutable
	public String getName(){
		return name;
	}
	
}
