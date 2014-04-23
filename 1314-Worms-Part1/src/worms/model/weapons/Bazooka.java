/**
 * A projectile class for the bazooka.
 * 
 * @invar	The yield must always be a valid yield.
 *  	  |	!validYield(getYield());
 * 
 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

package worms.model.weapons;

import worms.model.Projectile;
import worms.model.World;

public class Bazooka extends Projectile {
	
	/**
	 * All of the variables and constants used in this class.
	 */
	
	private final int APCOST = 50;
	private int yield;
	private final double MASS = 0.30;
	private final double MINIMALFORCE = 2.5;
	private final double MAXIMALFORCE = 9.5;
	private final int MAXIMALYIELD = 100;
	private final int DAMAGE = 80;
	
	/**
	 * Constructor for the bazooka projectile.
	 * 
	 * @param 	world
	 * 			The world the projectile belongs to.
	 * @param 	xPos
	 * 			The x-coordinate of the projectile.
	 * @param 	yPos
	 * 			The y-coordinate of the projectile.
	 * @param 	yield
	 * 			The yield used for this projectile
	 * @effect	The object super-class will be called with provided parameters.
	 * 		  |	super(world, xPos, yPos)
	 * @post	The current yield will equal the provided yield.
	 * 		  |	getYield() == yield;
	 */
	
	public Bazooka(World world, double xPos, double yPos, int yield){
		super(world, xPos, yPos);
		if(!validYield(yield)){
			throw new IllegalArgumentException("invalid yield");
		}
		this.yield = yield;
	}
	
	/**
	 * Return the number of actionpoints require to fire this projectile.
	 * 
	 * @return APCost.
	 */
	
	public int getAP(){
		return APCOST;
	}
	
	/**
	 * Return the mass of this projectile.
	 * 
	 * @return Mass.
	 */
	
	public double getMass(){
		return MASS;
	}
	
	/**
	 * Return the yield of this projectile.
	 * @return	yield.
	 */
	
	public int getYield(){
		return yield;
	}
	
	/**
	 * Check whether the provided yield is a valid yield.
	 * 
	 * @param 	yield
	 * 			The yield that is going to be checked.
	 * @return	True if the yield is bigger or equal to 0, and smaller than the maximalyield.
	 * 		  |	if(yield >= 0 && yield <= MAXIMALYIELD)
	 */
	
	public boolean validYield(int yield){
		if(yield >= 0 && yield <= MAXIMALYIELD){
			return true;
		}
		return false;
	}
	
	/**
	 * Return the damage this projectile does to worm objects.
	 * 
	 * @return damage.
	 */
	
	public int getDamage(){
		return DAMAGE;
	}
	
	/**
	 * Return the force that this projectile will exert while shooting.
	 * 
	 * @return 	Force.
	 */
	
	public double getForce(){
		double F = MINIMALFORCE + ((yield*(MAXIMALFORCE - MINIMALFORCE))/100);
		return F;
	}

}
