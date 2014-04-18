package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class IllegalRadiusException extends RuntimeException {
	

	/**
	 * We'll first initialize, register and return
	 */
	
	private double radius;
	
	public IllegalRadiusException(double radius){
		this.radius = radius;
	}
	
	@Basic @Immutable
	public double getRadius(){
		return radius;
	}

}
