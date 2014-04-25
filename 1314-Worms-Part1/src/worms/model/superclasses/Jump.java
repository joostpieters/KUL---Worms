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
	
//	/**
//	 * Method to calculate the position an object would be after performing a jump.
//	 * 
//	 * @param 	time
//	 * 			The time the jump is executed for.
//	 * @return	2 Dimensional array containing the position.
//	 */
//	
//	public double[] jumpStep(double time){
//		if(getDirection() == 0 || getDirection() == Math.PI || getDirection() == 2*Math.PI || getDirection() == Math.PI/2){
//			return new double[]{getX(), getY()};
//		}
//		double[] positionPerTime = new double[2];
//		double v0 = ((getForce()/getMass())*0.5);
//		double v0x = (v0*Math.cos(getDirection()));
//		double v0y = (v0*Math.sin(getDirection()));
//		positionPerTime[0] = (getX()+(v0x*time));
//		positionPerTime[1] = (getY()+((v0y*time)-((9.80665*time*time/2))));
//		
//		return positionPerTime;
//	}
//	
//	/**
//	 * Method to perform a jump.
//	 * 
//	 * @param 	timeS
//	 * 			The timestep
//	 * @effect	The position will have changed according to jumpStep.
//	 * 		  | setX(jumpStep(jumpTime(timeS))[0]);
//			  | setY(jumpStep(jumpTime(timeS))[1]);
//	 */
//	
//	public void jump(double timeS){
//		if(getDirection() == 0 || getDirection() == Math.PI || getDirection() == 2*Math.PI || getDirection() == Math.PI/2){
//			throw new IllegalStateException("Can't jump at the current angle!");
//		}
//		setX(jumpStep(this.jumpTime(timeS))[0]);
//		setY(jumpStep(this.jumpTime(timeS))[1]);
//
//		
//	}

	
	/**
	 * Gets the mass of this specific projectile from the weapons-classes.
	 */
	
	public abstract double getMass();
	
	/**
	 * Gets the Force exerted on this projectile from the weapons-classes.
	 */
	
	public abstract double getForce();
	
	/**
	 * gets the direction of the worm.
	 */
	
	public abstract double getDirection();
	
	public abstract double jumpTime(double timeS);

}
