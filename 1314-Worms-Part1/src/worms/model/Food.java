package worms.model;
import worms.model.superclasses.Object;

public class Food extends Object {

	private final static double RADIUS = 0.20;
	
	public Food(World world, double x, double y){		
		super(world, x, y, RADIUS);
	}
	
	public Food(World world){
		this(world, 0.0, 0.0);
		setWorld(world);
		if(!world.suitablePos(this)){
			throw new IllegalStateException("Couldn't find a suitable position, love.");
		}
	}
}
