/**
 * A class of projectiles that can be specified, and are an Object.
 * 
 * @invar	Each worm must be a valid worm.
 * 		 |	getWorm() != null
 * @invar	The projectile will spawn in equal direction with of the worm.
 * 		 |	getWorm().getOrientation() == getOrientation();
 * 
 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

package worms.model;
import be.kuleuven.cs.som.annotate.*;
import worms.model.superclasses.Jump;

public abstract class Projectile extends Jump{
	
	/**
	 * All of the variables and constants used in this class.
	 */
	
	private Worm worm;
	private final int DENSITY = 7800;

	/**
	 * Constructor for the Projectile.
	 * 
	 * @param 	world
	 * 			The world the projectile is added to.
	 * @param 	xPos
	 * 			The initial X-coordinate of the Projectile.
	 * @param 	yPos
	 * 			The initial Y-coordinate of teh Projectile.
	 * @effect	Will call the Object superclass in model.superclasses.
	 */
	
	@Raw
	public Projectile(World world, double xPos, double yPos){
		super(world, xPos, yPos, 0.0);
		this.setRadius(getRadius());
	}
	
	/**
	 * Set the owner (worm) of this projectile.
	 * 
	 * @param 	worm
	 * 			The worm the projectile belongs to.
	 * @post	The current worm will equal the parameter worm.
	 * 		  |	getWorm() == worm
	 * @throws	IllegalArgumentException if the worm is null and therefor doesn't exist.
	 * 		  |	worm != null;
	 */
	
	@Raw
	public void setCurrentWorm(Worm worm){
		if(worm == null){
			throw new IllegalArgumentException("The worm you're trying to set as owner is null.");
		}
		this.worm = worm;
	}
	
	/**
	 * Getter for the worm that this projectile belongs to.
	 * 
	 * @return	The worm
	 * 		  |	result == this.worm;
	 */
	
	@Basic
	public Worm currentWorm(){
		return this.worm;
	}
	
	
	/**
	 * Getter for the direction of the current worm, and therefor also the projectile due to the invar.
	 * 
	 * @return	The direction of the current worm.
	 * 		  |	result == getWorm().getOrientation();
	 */
	
	@Basic @Raw
	public double getDirection(){
		return this.currentWorm().getOrientation();
	}
	
	/**
	 * Getter for the radius of this projectile.
	 * 
	 * @return	The radius of this projectile.
	 * 		  |	result == Math.cbrt((this.getMass()*0.75)/(DENSITY*Math.PI));
	 */
	
	@Basic @Raw
	public double getRadius(){
		return Math.cbrt((this.getMass()*0.75)/(DENSITY*Math.PI));
	}
	
	/**
	 * Method to make a projectile jump, and therefor shoot.
	 * 
	 * @param	time
	 * 			The time that this projectile will jump.
	 * @effect	Will call the Jump superclass with given time as parameter.
	 * 		  |	super.jump(time);
	 * @effect	Post jump, the Projectile will damage worms within it's radius and remove itself.
	 * 		  | damage(); remove();
	 */
	
	public void shoot(){
		jump(jumpTime(1e-4));
		damage();
		remove();
	}
	
	/**
	 * Method to find worms within the projectile's radius and damage them according to the weapons.
	 * 
	 * @effect	All worms within the world and within the Projectile's radius will be damaged.
	 * 		  |	worm.setHitPoints(worm.getHitPoints() - this.getDamage());	 * 
	 */
	
	public void damage(){
		if(isActive()){
			for(Worm worm : this.getWorld().getWorms()){
				if(this.overlaps(worm)){
					worm.setHitPoints(worm.getHitPoints() - this.getDamage());
				}
			}
		}
	}
	
	/**
	 * Method to calculate the position an object would be after performing a jump.
	 * 
	 * @param 	time
	 * 			The time the jump is executed for.
	 * @return	2 Dimensional array containing the position.
	 */
	
	public double[] jumpStep(double time){
		if(time < 0){
			throw new IllegalArgumentException("Time is less than 0");
		}
		double v0 = getInitialVelocity();
		double vX = v0 * Math.cos(getDirection());
		double vY = v0 * Math.sin(getDirection());
		double dX = getX() + (vX * time);
		double dY = getY() + (vY * time - 0.5*9.80665*Math.pow(time, 2));
		double[] step = {dX, dY};
		return step;
	}
	
	/**
	 * Method to perform a jump.
	 * 
	 * @param 	timeS
	 * 			The timestep
	 * @effect	The position will have changed according to jumpStep.
	 * 		  | setX(jumpStep(jumpTime(timeS))[0]);
			  | setY(jumpStep(jumpTime(timeS))[1]);
	 */
	
	public void jump(double timeS){
 		double t = (getRadius()/(getInitialVelocity()*4));
		double[] temp = jumpStep(t);
		double dX = temp[0];
		double dY = temp[1];
		while(!getWorld().isImpassable(dX, dY, getRadius()) && getWorld().objectInWorld(dX, dY, getRadius())){
			t += timeS;
			temp = jumpStep(t);
			dX = temp[0];
			dY = temp[1];
		}
		if(!getWorld().objectInWorld(dX, dY, getRadius())){
			this.remove();
		}
		else{
			setX(dX);
			setY(dY);

		}
	}

	public double jumpTime(double timeS){
		double time = (getRadius()/getInitialVelocity());
		double[] temp = jumpStep(time);
		double dX = temp[0];
		double dY = temp[1];
		while(!getWorld().isImpassable(dX, dY, getRadius())){
			time = time + timeS;
			temp = jumpStep(time);
			dX = temp[0];
			dY = temp[1];
		}
		return time;
	}
	
	private double getInitialVelocity(){
		double v0 = (getForce()/getMass())*0.5;
		return v0;
	}
	
	/**
	 * Gets the mass of this specific projectile from the weapons-classes.
	 */
	
	public abstract double getMass();
	
	/**
	 * Gets the Force exerted on this projectile from the weapons-classes.
	 */
	
	public abstract double getForce();
	
	/**
	 * gets the Damage done by this specific projectile from the weapons-classes.
	 */
	
	public abstract int getDamage();
	
	/**
	 * Gets the actionpoints required to fire this projectile from the weapons-classes.
	 */
	
	public abstract int getAP();
	
}
