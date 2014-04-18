package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class IllegalDirectionException extends Exception {
	
	/**
	 * We'll first initialize, register and return
	 */
	
	private double direction;
	
	public IllegalDirectionException(double direction){
		this.direction = direction;
	}
	
	@Basic @Immutable
	public double getDirection(){
		return direction;
	}
}
