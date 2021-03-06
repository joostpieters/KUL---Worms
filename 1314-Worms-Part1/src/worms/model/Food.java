package worms.model;
import be.kuleuven.cs.som.annotate.*;
import worms.model.superclasses.Object;

/**
 * A class of Foods, that are Objects.

 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

public class Food extends Object {

	/**
	 * All of the variables and constants used in this class.
	 */
	
	private final static double RADIUS = 0.20;
	
	/**
	 * Constructor for a food-object.
	 * 
	 * @param 	world
	 * 			The world this food will belong to and is spawned in.
	 * @param	x
	 * 			The initial x-coordinate of this food-object.
	 * @param 	y
	 * 			The initial y-coordinate of this food-object.
	 * @effect	The superclass Object will be called using the parameters and a static radius.
	 * 		  |	super(world, x, y RADIUS);
	 */
	@Raw
	public Food(World world, double x, double y){		
		super(world, x, y, RADIUS);
	}
	
	@Basic @Raw
	public static double getR(){
		return RADIUS;
	}

}
