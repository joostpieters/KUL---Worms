package worms.model;

import worms.model.superclasses.Object;

public class Food extends Object {

	private final static double RADIUS = 0.20;
	
	public Food(World world, double x, double y){		
		super(world, x, y, RADIUS);
	}
}
