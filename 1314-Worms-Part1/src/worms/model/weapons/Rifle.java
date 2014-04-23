/**
 * A projectile class for the Rifle.
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

public class Rifle extends Projectile {
	
	/**
	 * All of the variables and constants used in this class.
	 */
	
	private final int APCOST = 10;
	private final double MASS = 0.01;
	private final double FORCE = 1.5;
	private final int DAMAGE = 20;
	
	/**
	 * Constructor to create a rifle projectile.
	 * 
	 * @param 	world
	 * 			The world that this projectile will belong to.
	 * @param 	xPos
	 * 			The x-coordinate of the projectile.
	 * @param 	yPos
	 * 			The y-coordinate of the projectile.
	 */
	
	public Rifle(World world, double xPos, double yPos){
		super(world, xPos, yPos);
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
	 * Return the damage this projectile does to worm objects.
	 * 
	 * @return Damage.
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
		return FORCE;
	}

}
