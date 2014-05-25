/**
 * A super-class for jumping.
 * 
 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

package worms.model.superclasses;

import worms.model.World;

public abstract class Jump extends Object{

	/**
	 * Constructor to create the object that we are going to jump with.
	 * 
	 * @param 	world
	 * 			The world of this object.
	 * @param 	xPos
	 * 			The x-coordinate.
	 * @param 	yPos
	 * 			The y-coordinate.
	 * @param 	radius
	 * 			The radius of this object.
	 * @effect 	Call the object superclass with given parameters.
	 * 		  | super(world, xPos, yPos, radius);
	 */

	public Jump(World world, double xPos, double yPos, double radius){
		super(world, xPos, yPos, radius);
	}


	/**
	 * Gets the mass of this specific projectile from the weapons-classes.
	 */

	public abstract double getMass();

	/**
	 * gets the direction of the worm.
	 */

	public abstract double getDirection();

	public abstract double jumpTime(double timeS);

}