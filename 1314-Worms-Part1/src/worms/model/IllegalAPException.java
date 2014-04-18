package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class IllegalAPException extends Exception {
	
	/**
	 * We'll first initialize, register and return
	 */
	
	private int	AP;
	
	public IllegalAPException(int AP){
		this.AP = AP;
	}
	
	@Basic @Immutable
	public int getAP(){
		return AP;
	}
}
