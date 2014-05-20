/**
 * A super-class of Objects.
 * 
 * @invar	Each object must be in a valid world.
 * 		 |	getWorld() != null;
 * @invar	Each object must have valid coordinates.
 * 		 |	!isValidPos(getX(), getY();
 * @invar	The radius must be valid at all times.
 * 		 |	!isValidRadius(getRadius());
 * 
 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

package worms.model.superclasses;

import worms.model.World;
import worms.util.Util;

public abstract class Object {
	
	/**
	 * All of the variables and constants used in this class.
	 */
	
	private boolean isRemoved;
	private double x, y, radius;
	private World world;
	
	/**
	 * Constructor to create an object.
	 * 
	 * @param 	world
	 * 			The world the object must be located in.
	 * @param 	xPos
	 * 			The x-coordinate for the object.
	 * @param 	yPos
	 * 			The y-coordinate for the object.
	 * @param 	radius
	 * 			The radius for this object.
	 * @effect	The current x, y will equal the xPos and yPos.
	 * 		  |	getX() == xPos && getY() = yPos;
	 * @effect	The new radius will equal the provided radius.
	 * 		  |	getRadius() == radius;
	 * @throws IllegalArgumentException This position is not valid.
	 * 		  | !isValidPos(xPos, yPos);
	 */

	public Object(World world, double xPos, double yPos, double radius){
		if(!isValidPos(xPos, yPos)){
			throw new IllegalArgumentException("This is not a valid position.");
		}
		this.x = xPos;
		this.y = yPos;
		setRadius(radius);
		setWorld(world);
		if(world.objectInWorld(getX(), getY(), getRadius())){
			this.remove();
		}
	}
	
	/**
	 * Getter for the x-coordinate.
	 * 
	 * @return x
	 */
	
	public double getX(){
		return x;
	}
	
	/**
	 * Getter for the y-coordinate.
	 * 
	 * @return y
	 */
	
	public double getY(){
		return y;
	}
	
	/**
	 * Method to set the x-coordinate
	 * 
	 * @param 	newX
	 * 			The new x-coordinate.
	 * @effect	The nnew x will equal newX.
	 * 		  |	getX() == newX
	 * @throws IllegalArgumentException This position is not valid.
	 * 		  | !Double.isNaN(newX);
	 */
	
	public void setX(double newX){
		if(Double.isNaN(newX)){
			throw new IllegalArgumentException("This is not a valid xPos.");
		}
		this.x = newX;

	}
	
	/**
	 * Method to set the world for the current object.
	 * 
	 * @param 	world
	 * 			The world for this object.
	 * @effect	The current world will equal this world.
	 * 		  |	getWorld() == world;
	 * @effect	This object will be added to the list of objects in this world.
	 * 		  |	getWorld().addObjects(this);
	 */
	
	public void setWorld(World world){
		if(world == null){
			throw new IllegalArgumentException("Not a valid world!");
		}
		if(world != null){
			this.world = world;
			getWorld().addObjects(this);
		}

		
	}
	
	/**
	 * Method that returns the world of the current object.
	 * 
	 * @return	The current world.
	 */
	
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Method to set the y-coordinate
	 * 
	 * @param 	newY
	 * 			The new y-coordinate.
	 * @effect	The nnew y will equal newX.
	 * 		  |	getY() == newY
	 * @throws IllegalArgumentException This position is not valid.
	 * 		  | !Double.isNaN(newY);
	 */
	
	public void setY(double newY){
		if(!Double.isNaN(newY)){
			this.y = newY;
		}
	
	}
	
	/**
	 * This method checks whether a certain position is valid.
	 * 
	 * @param 	x
	 * 			x-coordinate
	 * @param 	y
	 * 			y-coordinate
	 * @return	true if both coordinates are numbers.
	 * 		  | !Double.isNaN(x) && !Double.isNaN(y)
	 */
	
	public boolean isValidPos(double x, double y){
		return (!Double.isNaN(x) && !Double.isNaN(y));
	}
	
	/**
	 * This method returns the radius of this object.
	 * 
	 * @return The current radius
	 */
	
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Method that sets the radius of the current object.
	 * 
	 * @param 	radius
	 * 			The new radius.
	 * @effect	The new radius will equal this radius.
	 * 		  |	getRadius() == radius;
	 * @throws IllegalArgumentException This is not a valid radius.
	 * 		  | Double.isNaN(getRadius());
	 */
	
	public void setRadius(double radius){
		if(Double.isNaN(radius)){
			throw new IllegalArgumentException("Not a valid radius!");
		}
		this.radius = radius;
	}
	
	/**
	 * Method to check whether an object is removed or not.
	 * 
	 * @return	True if the object is removed.
	 */
	
	public boolean isActive(){
		return !isRemoved;
	}
	
	/**
	 * Method to remove an object.
	 * 
	 * @effect 	If the list of gameobjects contains this object, it will be removed.
	 * 		  |	if(getWorld().getObjects().contains(this)) remove();
	 * @effect	The world for this object will be reset.
	 * 		  | setWorld() == null;
	 */
	
	public void remove(){
			World ex = this.getWorld();
			//ex.delObjects(this);
			this.world = null;
			this.isRemoved = true;

	}
	
	/**
	 * Method to check whether this object overlaps with another object.
	 * 
	 * @param 	object
	 * 			The object to overlap with.
	 * @return	True if the object overlaps, thus is within the sum of the 2 radiuses away from this object.
	 * 		  |	(getX() - object.getX()) <= (getRadius() + object.getRadius()) || (getY() - object.getY()) <= (getRadius() + object.getRadius());
	 */
	
	public boolean overlaps(Object object){
		if(Util.fuzzyEquals(this.getX(), object.getX(), 0.40) && Util.fuzzyEquals(this.getY(), object.getY(), 0.40)){
			return true;
		}
		return false;
	}

}
