/**
 * A class of Foods, that are Objects.

 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

package worms.model;
import worms.model.superclasses.Object;

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
	public Food(World world, double x, double y){		
		super(world, x, y, RADIUS);
	}
	
	/**
	 * A more compact constructor for Food, used to give it a random spawn position.
	 * 
	 * @param 	world
	 * 			The world this food will belong to and is spawned in.
	 * @effect	The food will have a random spawn position.
	 * 		  |	suitablePos(this);
	 * @effect	The other constructor will be called with given parameters and initial position at the origin.
	 * 		  |	this(world, 0.0, 0.0)
	 * @throws 	IllegalStateException A suitable position could not be found.
	 */
	
	public Food(World world){
		this(world, 0.0, 0.0);
		setWorld(world);
		if(!world.suitablePos(this)){
			throw new IllegalStateException("Couldn't find a suitable position.");
		}
	}
}
