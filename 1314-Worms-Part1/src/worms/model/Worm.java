package worms.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import worms.model.superclasses.Jump;
import worms.model.weapons.Bazooka;
import worms.model.weapons.Rifle;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of worms that can be manipulated, one of the main elements in the game.
 * 
 * @invar	Each worm must have a valid name
 * 		 |	isValidName(getName())
 * @invar	Each worm must have a radius bigger than 0.25 metres.
 * 		 |	Worm.radius >= 0.25
 * @invar	The position of each worm must resemble a valid X and Y coordinate.
 * 		 |  isValidPosition(worm)
 * 
 * @version 2.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

public class Worm extends Jump {
	
	/**
	 * All of the variables and constants used in this class.
	 */
	
	private static String[] names = { "Podrick", "Cartman", "Kenny", "KEVIN", "Towly", "Kris", "Reinout", "Jorn", "Cedric", "Lieselotte", "Pjotr", "Vanessa", "Doctor", "Karen Love", "John", "Geoff", "Jennifer", "Philip", "Captain", "Ted", "Lily", "Marshall", "Barney", "Robin", "Zoe", "Frank", "Bill", "McSoap", "Chris", "Sam", "Lau", "Don", "Jim", "Andy", "James O'Hare", "2Slo", "2Pro", "Peter", "Bart", "Sensei", "Robb", "Arya", "Ann", "Ghost", "Solfatare", "Dietrich", "Lars", "Tom", "McAwesome", "Ayden", "Berta", "Daniel", "Matt", "David", "Hamish", "Capaldi", "Romero", "Calvin", "Bondi"};
	private double direction;
	private double radius = 0.25;
	private double mass = 0;
	private String name = " ";
	private static double minimalRadius = 0.25;
	private int actionPoints = 0;
	private int hitPoints;
	private Teams team;
	private String weapon;
	private static final double DENSITY = 1062;

	/*
	 * Initialize a Worm with given position, direction, radius and name.
	 * 
	 * @param  	x
	 * 		  	The initial X-coordinate of the worm.
	 * @param 	y
	 * 		  	The initial Y-coordinate of the worm.	
	 * @param 	direction
	 * 		  	The initial direction of the worm in radians.
	 * @param 	radius
	 * 		  	The initial radius of the worm.
	 * @param 	name
	 * 		  	The name that will be displayed for the worm.
	 * @pre 	The initial name must be a valid name conform to given standards
	 * 		  | isValidName(getName())
	 * @pre		The initial radius must be bigger than the minimal radius.
	 * 		  | radius >= getMinimalRadius()
	 * @effect	The new x-coordinate will equal x.
	 * 		  |	getX() == x;
	 * @effect	The new y-coordinate will equal y.
	 * 		  |	getY() == y;
	 * @effect	The new orientation will equal the direction.
	 * 		  |	getOrientation() == direction;
	 * @effect 	The new radius will equal the radius.
	 * 		  |	getRadius() == radius;
	 * @effect	The new name will equal the name.
	 * 		  |	getName() == name;
	 * @effect	The new amount of actionpoints will equal the maximum amount of actionpoints.
	 * 		  |	getActionPoints() == getMaxActionPoints()
	 * @effect	The superclass Object will be called with given parameters.
	 * 		  |	super(world, x, y, radius);
	 * @effect	The worm's initial weapon will be set to Rifle.
	 * 		  |	setWeapons("Rifle");
	 */
	
	@Raw
	public Worm(World world, double x, double y, double direction, double radius, String name){
		super(world, x, y, radius);
		setOrientation(direction);
		rename(name);
		setActionPoints(getMaxActionPoints());
		setHitPoints(getMaxHitPoints());
		setTeam(world.getActiveTeam());
		setWeapon("Rifle");
		if (getTeam() != null){
			getTeam().addWorm(this);
		}
	}
	
	/**
	 * Slightly compacter constructor to create a worm with a random position.
	 * 
	 * @param 	world
	 * 			The world this worm will be created in.
	 * @param 	x
	 * 			The x-coordinate of this worm.
	 * @param 	y
	 * 			The y-coordinate of this worm.
	 * @effect	A worm will be created with a radius equal to the minimal radius and a random name from the nameslist.
	 * 		  |	this(world, x, y, Math.PI/2, getMinimalRadius(), names[(int)Math.floor(Math.random()*names.length)]);
	 */
	
	@Raw
	public Worm(World world, double x, double y){
			this(world, x, y, Math.PI/2, getMinimalRadius(), names[(int)Math.floor(Math.random()*names.length)]);
	}
	
	/**
	 * Check whether a name is conform to given standards
	 * 
	 * @param 	name
	 * 			The string to check.
	 * @return	True if the name contains only letters, spaces and quotationmarks.
	 */
	
	@Raw
	public static boolean isValidName(String name){
		String validLetters = "\"\' abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		boolean validName = true;
		int lenght = name.length();
		for(int i = 0; i < lenght; i++){
			if(!validLetters.contains(name.subSequence(i, i + 1))){
				validName = false;
				i = lenght;
			}
			
		}
		return validName;
	}
	
	
	/**
	 * Change or set the radius of a Worm to the provided radius.
	 * 
	 * @param 	newRadius
	 * 			The new radius the worm needs to be in metres.
	 * @post	The new radius will be equal to the newRadius.
	 * 		  |	new.getRadius() == newRadius;
	 * @effect	The mass and maximum amount of actionpoints for this worm will be calculated.
	 * 		  | calcMass(); calcMaxAP;
	 * @throws	IllegalRadiusException	The given newRadius is not valid.
	 * 		  	
	 */
	
	@Raw
	public void setRadius(double newRadius) throws IllegalArgumentException{
		if(!isValidRadius(newRadius)){
			throw new IllegalArgumentException(newRadius + " is an illegal value for the radius");
		}
		super.setRadius(newRadius);
		if(getActionPoints() > getMaxActionPoints()){
			setActionPoints(getMaxActionPoints());
		}
	}

	/**
	 * Calculate and set the mass for this worm.
	 * 
	 * @post	The mass will equal the newly calculated mass.
	 * 		  |	new.mass == getMass();
	 */
	
	public void calcMass(){
		mass = (DENSITY*((4.0*Math.PI*(this.getRadius()*this.getRadius()*this.getRadius())/3.0)));
	}
	
	/**
	 * Return the mass of a worm in kilograms.
	 * 
	 * @return	The current mass of given worm.
	 */
	
	@Basic @Raw
	public double getMass(){
		calcMass();
		return mass ;
	}
	
	/**
	 * Return the radius of a worm in metres.
	 * 
	 * @return	The current radius of given worm.
	 */
	
	@Basic @Raw
	public double getRadius(){
		return radius;
	}
	
	/**
	 * Return the minimal radius a Worm needs to have.
	 * 
	 * @return	The minimal radius of given worm.
	 */
	
	@Basic
	public static double getMinimalRadius(){
		return minimalRadius;
	}
	
	/**
	 * Method to check whether the radius is a valid radius.
	 * 
	 * @param 	rad
	 * 			The radius that is going to be checked.
	 * @return	True if the radius is bigger than the minimal radius.
	 * 		  |	result == rad>=getMinimalRadius();
	 */
	
	@Raw
	public static boolean isValidRadius(double rad){
		return (rad >= getMinimalRadius() && !Double.isNaN(rad));
	}

	
	/**
	 * Set the direction of a worm.
	 * 
	 * @param	direction
	 * 			The direction we're changing to.
	 * @post	The new direction will equal direction.
	 * 		  |	new.getOrientation() == direction;
	 */
	
	@Raw
	public void setOrientation(double direction){
		if(!Double.isNaN(direction)){
			if(direction <= 0.0){
				direction += 2*Math.PI;
				setOrientation(direction);
			}
			else if(direction > 2*Math.PI){
				direction -= 2*Math.PI;
				setOrientation(direction);
			}
			else{
				this.direction = direction;
			}
		}
	}
	
	/**
	 * Return the current orientation of a worm.
	 * 
	 * @return	The current direction the worm is oriented at.
	 */
	
	@Basic
	public double getOrientation(){
		return direction;
	}
	
	/**
	 * Return the name of a worm.
	 * 
	 * @return	The name of given worm.
	 */
	
	@Basic
	public String getName(){
		return name;
	}
	
	/**
	 * Return the maximum of actionpoints a worm can have.
	 * 
	 * @return 	The maximum actionpoints given worm can have.
	 * @post	The new maximum of actionpoints will be equal to the mass rounded up.
	 * 		  |	getMaxActionPoints() == Math.round(getMass());
	 */
	
	@Raw
	public int getMaxActionPoints(){
		return (int) Math.round(getMass());
	}
	
	/**
	 * Return the maximum number of hitpoints.
	 *
	 * @return 	The maximum mount of hitpoints a worm can have.
	 * @post	The maximum amount of hitpoints will equal the mass rounded up.
	 * 		  |	getMaxHitPoints() == Math.round(getMass());
	 */
	@Raw
	public int getMaxHitPoints(){
		return (int) Math.round(getMass());
	}
	
	/**
	 * Return the current number of hitPoints.
	 * 
	 * @return	The current number of hitpoints for a worm.
	 */
	
	@Raw @Basic 
	public int getHitPoints(){
		return hitPoints;
	}
	
	/**
	 * Return the current actionpoints of a worm that are left.
	 * 
	 * @return	The current number of actionpoints given worm has.
	 */
	
	@Raw @Basic
	public int getActionPoints(){
		return actionPoints;
	}
	
	/**
	 * Give a worm a new name that has to abide certain standards.
	 *  
	 * @param 	newName
	 * 			The new name we would like to give to given worm.
	 * @post	The new name of this worm will equal newName
	 * 		  |	new.getName() == newName;
	 */
	
	public void rename(String newName) throws IllegalArgumentException{
			if(!isValidName(newName)){
				throw new IllegalArgumentException(name + " is not a valid name!");
			}
			this.name = newName;
		
	}

	/**
	 * Check whether a worm is able to move.
	 * 
	 * @return 	True if and only if a valid fall location can be found..
	 * 		  |	searchFallLocation(getRadius());
	 */
	public boolean canMove(){
		boolean canMove = true;
		if(isActive()){
			double currentDistance = getRadius();
			double[] newLocation = null;
			while (newLocation == null && currentDistance >= 0.1){
				newLocation = searchFallLocation(currentDistance);
				currentDistance -= 0.01;
			}
			if (newLocation == null){
				canMove = false;
			}
		}
		return canMove;
	}
		
	/**
	 *  Method to find a suitable location to move to.
	 * 
	 * @param 	distance
	 * 			The distance downwards.
	 * @return 	The set of coordinates for a suitable location.
	 */
		
	@Model
	public double[] searchFitLocation(double distance) {
		double thetaUp = this.getDirection();
		double thetaDown = this.getDirection();
		double tempX = this.getX() + distance*Math.cos(this.getDirection());
		double tempY = this.getY() + distance*Math.sin(this.getDirection());
		while ((Math.abs(thetaUp-getDirection()) < 0.7875) && (this.getWorld().isAdjacent(tempX,tempY,this.getRadius()))){
			thetaUp += 0.0175;
			tempX = this.getX() + distance*Math.cos(thetaUp);
		    tempY = this.getY() + distance*Math.sin(thetaUp);
	        if(!(this.getWorld().isAdjacent(tempX, tempY,getRadius()))){	
				thetaDown -= 0.0175;
			    tempX = this.getX() + distance*Math.cos(thetaDown);
		        tempY = this.getY() + distance*Math.sin(thetaDown); 
	        }
	    }
		if (!this.getWorld().isAdjacent(tempX,tempY,getRadius())){
			return new double[] {tempX,tempY};	
		}
		return null;
	}
		
	/**
	 * Method to find a suitable location to land after performning a fall.
	 * 
	 * @param 	distance
	 * 			The distance downwards.
	 * @return 	The set of coordinates for a suitable location.
	 */
		
		public double[] searchFallLocation(double distance) {
			double thetaUp = this.getDirection();
			double thetaDown = this.getDirection();
			double tempX = this.getX() + distance*Math.cos(this.getDirection());
			double tempY = this.getY() + distance*Math.sin(this.getDirection());
			while ((Math.abs(thetaUp-getDirection()) < 0.7875) && (this.getWorld().isImpassable(tempX,tempY,this.getRadius()))){
				thetaUp += 0.0175;
				tempX = this.getX() + distance*Math.cos(thetaUp);
			    tempY = this.getY() + distance*Math.sin(thetaUp);
		        if(!(this.getWorld().isImpassable(tempX, tempY,getRadius()))){	
					thetaDown -= 0.0175;
				    tempX = this.getX() + distance*Math.cos(thetaDown);
			        tempY = this.getY() + distance*Math.sin(thetaDown); 
		        }
		    }
			if (!this.getWorld().isImpassable(tempX,tempY,getRadius())){

				return new double[] {tempX,tempY};	
			}
			return null;
		}
		
	/**
	 * Make a worm move a certain amount of steps conform to his current orientation.
	 * 
	 * @param 	steps
	 * 			The amount of steps given worm is going to move.
	 * @post	The x and y coordinates will have changed according to the number of steps.
	 * @effect	The worm will eat if there is food on his fall location.
	 * @throws 	IllegalStateException() Cannot move this way.
	 * 		  |	!canMove(steps);
	 */
		
		public void move() throws IllegalStateException{
			double currentDistance = getRadius();
			double[] newLocation = null;
			while (newLocation == null && currentDistance>=0.1){
				newLocation = searchFitLocation(currentDistance);
				currentDistance -= 0.01;
			}
			currentDistance = getRadius();
			while (newLocation == null && currentDistance>=0.1){
				newLocation = searchFallLocation(currentDistance);
				currentDistance -= 0.01;
			}
			if (!(newLocation == null)){
				double newX = newLocation[0];
				double newY = newLocation[1];
				double slope = Math.atan((getY() - newY)/(getX() - newX));
				int newAP = (int) Math.round(getActionPoints() - (Math.abs(Math.cos(slope)) + Math.abs(4*Math.sin(slope))));
				if (newAP >= 0){
					setX(newX);
					setY(newY);
					fall();
					setActionPoints(newAP);
					try{
						eat();
					}
					catch(Exception e){
					if(getHitPoints() <= 0){
						this.remove();
					}
				}
				}
				else{
					throw new IllegalStateException("Not enough actionpoints");
				}
			}
		}
		
	/**
	 * Method to make the worm fall and make him loose a number of HP according to the height.
	 * 
	 * @effect 	The worm shall move to the impassable terrain under him.
	 * 		  | (getWorld().isAdjacent(getX(),getY(),getRadius()))&& ! (getWorld().objectInWorld(getX(), getY(),getRadius())) && this.getWorld().isImpassable(getX(), getY(), getRadius())
	 * @effect	The worm shall loose HP.
	 * 		  | new.getHitPoints() == this.getHitPoints() - 3 * distance
	 * @effect	 The new hitPoints will always be bigger than or equal to 0.
	 * 		  |	if(getHitPoints()-3*(getY()-new.getY())< 0){setHitPoints(0));
	 */
		
		public void fall(){
			double oldY = getY();
			while (! (getWorld().isAdjacent(getX(),getY(),getRadius())) && (getWorld().objectInWorld(getX(), getY(),getRadius()))){
				setY(getY() - (getWorld().heightPXL()/2));
			if (getWorld().isAdjacent(getX(), getY(), getRadius())){
				int distance = (int) (oldY - getY());
				System.out.println("Distance: "+distance + " and HP: "+getHitPoints());
				int newHitPoints = getHitPoints() - 3*distance;
				if (newHitPoints > 0){
					setHitPoints(newHitPoints);
				}
				else if(newHitPoints <= 0){
					setHitPoints(0);
				}
			}
			}
			if (!getWorld().objectInWorld(getX(), getY(), getRadius())){
				this.remove();
			}

		}
		
	/**
	 * Method to make the worm fall 1 pixel.
	 * 
	 * @effect 	The new y-coordinate will equal the old y minus the distance of 1 pixel.
	 * 		  | new.getY() = this.getY() - getWorld.heightPXL();
	 */
	
		public void fallDown(){
			double distance = getWorld().heightPXL()/2;
			setY(getY() - distance);
		}

	/**
	 * Change the Actionpoints of a worm.
	 * 
	 * @param 	actionPoints
	 * 			The new amount of actionpoints.
	 * @post	The actionpoints provided in the parameter will equal the new amount of actionPoints.
	 */
	
	@Model @Raw
	public void setActionPoints(int actionPoints){
		if(actionPoints < 0){
			this.actionPoints = 0;
		}
		else if(actionPoints > getMaxActionPoints()){
			this.actionPoints = getMaxActionPoints();
		}
		else{
			this.actionPoints = actionPoints;
		}
	}
	
	/**
	 * Change the amount of hitpoints of a worm.
	 * 
	 * @param 	hitPoints
	 * 			The new amount of hitpoints.
	 * @post 	The amount of hitPoints will equal the parameter hitPoints.
	 */
	
	@Model @Raw
	public void setHitPoints(int hitPoints){
		if(hitPoints < 0){
			this.hitPoints = 0;
		}
		else if(hitPoints > getMaxHitPoints()){
			this.hitPoints = getMaxHitPoints();
		}
		else{
			this.hitPoints = hitPoints;
		}
	}
	
	/**
	 * Check whether a worm is able to turn.
	 * 
	 * @param 	angle
	 * 			The angle we would like to turn given worm with.
	 * @return	True if the cost of the turn is smaller than the actionpoints that remain.
	 * 		  |	result == (getActionPoints()>=(int)(Math.ceil(angle*(60/(2*Math.PI))))
	 */
	
	@Raw
	public boolean canTurn(double angle){
		boolean canTurn = true;
		if(Math.abs(angle) > Math.PI || angle == 0){
			canTurn = false;
		}
		int turnCost = (int)(Math.ceil(angle*(60/(2*Math.PI))));
		if(turnCost > getActionPoints()){
			canTurn = false;
		}
		return canTurn;
	}
	
	/**
	 * Make a worm turn by adding or subtracting a certain angle from/to its current direction.
	 * 
	 * @param 	angle
	 * 			The angle we would like given worm to turn.
	 * @pre		You must have sufficient amount of ActionPoints
	 * 		  |	canTurn = true;
	 * @post	The worm will be facing at a new angle consisting of the previous angle
	 * 			with the angle specified as parameter added to it.
	 */
	
	public void turn(double angle){
		if(canTurn(angle)){
			double placebo = getOrientation() + angle;
			if(!isValidDirection(placebo)){
				if(placebo > 2*Math.PI){
					placebo -= 2*Math.PI;
				}
				if(placebo < 0){
					placebo += 2*Math.PI;
				}
			}
		setOrientation(placebo);
		setActionPoints(getActionPoints()-(Math.abs((int)(angle*(60/(2*Math.PI))))));
		}
	}
	
	/**
	 * Return the Force that's going to be exerted on a worm during a jump.
	 * 
	 * @return	The force that needs to be exerted given said conditions.
	 * 		  |	result == ((5*actionPoints)+(mass*9.80665))
	 */
	
	@Basic @Raw
	public double getJumpForce(){
		return ((5*getActionPoints())+(getMass()*9.80665));
	}

	/**
	 * Return the time it will take to potentially make a jump with respect to the worms details.
	 * 
	 * @return	The time a potential jump will take.
	 * 		  |	result == distance/(initialVelocity*cos(initialDirection))
	 */
	
	public double getJumpTime(double timeStep){
		double[] newPos = new double[2];
		if(getActionPoints() == 0 || getOrientation() >= Math.PI || getOrientation() == 0){
			return 0;
		}
		boolean cannotPass = false;
		int cnt = 0;
		while(!cannotPass && !getWorld().objectInWorld(getX(), getY(), getRadius())){
			cnt++;
			newPos = jumpStep(cnt*timeStep);
			if(getWorld().isImpassable(newPos[0], newPos[1], getRadius())){
				cannotPass = true;
			}
		}
		boolean adPos = false;
		while((!adPos && cannotPass && !getWorld().objectInWorld(newPos[0], newPos[1], getRadius()))){
			cnt--;
			newPos = jumpStep(cnt*timeStep);			
			if(getWorld().isAdjacent(newPos[0], newPos[1], getRadius())){
				adPos = true;
				return cnt*timeStep;
			}
		}
		return cnt*timeStep;
	}
	
	/**
	 * Check whether a worm has enough actionpoints remaining to make a jump.
	 * 
	 * @return	False if the number of ActionPoints equals zero. True otherwise.
	 * 		  |	if(getActionPoints() == 0){
	 * 		  | 	return false;}
	 * 		  | else{return true;}	
	 */

	public boolean canJump(){
		boolean canJump = true;
		if(getActionPoints() == 0){
			canJump = false;
		}
		return canJump;
	}
	
	/**
	 * 
	 * @param 	direction
	 * 			The direction we'll check in.
	 * @return	True if the given direction is not below zero and not bigger than 2 times PI.
	 * 		  |			result == ((direction >= 0) && (direction < 2*Math.PI));
	 */
	
	public boolean isValidDirection(double direction){
		return ((direction >= 0) && (direction < 2*Math.PI));
	}
	
	/**
	 * Method to set the current team.
	 * 
	 * @param 	team
	 * 			The new team
	 * @post	The active team will equal this team.
	 * 		  | getActiveTeam() == team;
	 */
	
	@Basic @Raw
	public void setTeam(Teams team){
		this.team = team;
	}
	
	/**
	 * Getter to return the current team.
	 * 
	 * @return The current team.
	 */
	
	@Basic @Raw
	public Teams getTeam(){
		return team;
	}
	
	/**
	 * Method to select the next weapon in the weaponslist.
	 * 
	 * @effect	If the current weapon was the rifle then the weapon will be set to the Bazooka.
	 * 		  | setWeapon("Bazooka");
	 * @effect	If the current weapon was the Bazooka then the weapon will be set to the Rifle.
	 * 		  | setWeapon("Rifle");
	 * 
	 */
	
	public void selectNextWeapon(){
		switch(getWeapon()){
		case "Rifle":
			setWeapon("Bazooka");
			break;
		case "Bazooka":
			setWeapon("Rifle");
			break;
		default:
			setWeapon("Rifle");
		}
	}
	
	/**
	 * Return the current weapon.
	 * 
	 * @return	The current weapon.
	 */
	
	@Basic @Raw
	public String getWeapon(){
		return weapon;
	}
	
	/**
	 * Set the current weapon.
	 * 
	 * @param 	weapon
	 * 			The new weapon.
	 * @post	The current weapon will equal this weapon.
	 * 		  |	getActiveWeapon() == this;
	 */
	
	@Basic @Raw
	public void setWeapon(String weapon){
		this.weapon = weapon;
	}
	
	/**
	 * Check whether a worm can shoot.
	 * 
	 * @return 	True if there are more than 0 action points and if the world is passable at the current location.
	 * 		  | !(getActionPoints() == 0) && (!getWorld().isImpassable(getX(), getY(), getRadius()) && !removed())
	 */
	
	public boolean canShoot(){
		return (!(getActionPoints() == 0) && (!getWorld().isImpassable(getX(), getY(), getRadius()))); //add removed
	}
	
	/**
	 * Method that shoots a projectile.
	 * 
	 * @param 	yield
	 * 			The projectiles yield.
	 * @effect	If the current weapon is a bazooka, a bazookaprojectile will be created.
	 * 		  |	if(getWeapon() == "Bazooka"){proj = new Bazooka(getWorld(), shootX, shootY, yield);}
	 * @effect	If the current weapon is a rifle, a rifleprojectile will be created.
	 * 		  |	if(getWeapon() == "Rifle"){proj = new Rifle(getWorld(), shootX, shootY);}
	 * @effect	The actionpoints of the worm will be reduced with the weapon's firingcost.
	 * 		  |	setActionPoints(getActionPoints()-proj.getAP());
	 * @effect	The active projectile will equal the current projectile.
	 * 		  |	getActiveProjectile() == proj;
	 * @effect	The current worm will be set as the worm for this projectile.
	 * 		  |	proj.setCurrentWorm(this);
	 * @throws	IllegalStateException The worm is not able to shoot.
	 * 		  |	!canShoot();
	 */
	
	public void shoot(int yield){
		if(!canShoot()){
			throw new IllegalStateException("Not able to fire. Get into the bunkers!");
		}
		double shootX = this.getX()+Math.cos(this.getOrientation())*getRadius();
		double shootY = this.getY()+Math.sin(this.getOrientation())*getRadius();
		Projectile proj = null;
		if(getWeapon() == "Bazooka"){
			proj = new Bazooka(getWorld(), shootX, shootY, yield);
			if(proj.getWorld() != this.getWorld()){
				proj.setWorld(getWorld());
			}	
			}
		if(getWeapon() == "Rifle"){
			proj = new Rifle(getWorld(), shootX, shootY);
			if(proj.getWorld() != this.getWorld()){
				proj.setWorld(getWorld());
			}
		}
		proj.setCurrentWorm(this);
		proj.shoot();
		setActionPoints(getActionPoints()-proj.getAP());
		getWorld().setActiveProjectile(proj);

	}
	
	/**
	 * Method to add a team to the list of teams in this world.
	 * 
	 * @param 	team
	 * 			The team to add.
	 * @post	The list of teams will contain this team.
	 * 		  |	getTeams().contains(team);
	 */
	
	public void joinTeam(Teams team){
		team.addWorm(this);
	}

	/**
	 * Method to get the direction of the current worm.
	 * 
	 * @return 	The orientation
	 * 		  | getOrientation();
	 * 
	 */
	
	@Basic @Raw
	public double getDirection() {
		return getOrientation();
	}

	/**
	 * Method to get the jumping force of an object.
	 * 
	 * @return 	The jumping force.
	 * 		  |	((5*getActionPoints()) + (getMass()*9.80665))
	 * 
	 */
	
	public double getForce() {
		return ((5*getActionPoints()) + (getMass()*9.80665));
	}
	
	/**
	 * Method to remove a worm.
	 * 
	 * @effect 	The super class is called for his remove method.
	 * 		  |	super.remove();
	 * @effect	The worm is remove from it's team.
	 * 		  | getTeam().removeWorm(this);
	 * 
	 */
	
	public void remove(){
		super.remove();
		try{
			getTeam().removeWorm(this);
		}
		catch(Exception e){
			
		}
	}
	
	/**
	 * Method to eat if the worm is overlapping with food.
	 * 
	 * @effect 	All the food in the world is scanned and checked if it overlaps with the current worm and removed if it does. The radius of the worm is edited accordingly.
	 * 		  |	for(Food food : getWorld().getFood()){
	 *		  |		if(overlaps(food)){
	 *		  |		setRadius(1.1*getRadius());
	 *		  |		food.remove();}}}}
	 * 
	 */
	
	public void eat(){
			for(Food food : getWorld().getFood()){
			if(overlaps(food)){
				getWorld().delFood(food);
				setRadius(1.1*getRadius());
				food.remove();
				System.out.println("ATE");
			}
		}
	}
	
	
	/**
	 * Method to calculate the time needed for this jump.
	 * 
	 * @param 	timeS
	 * 			The timestep.
	 * @return	The time that a jump will be executed for.
	 */
	
	public double jumpTime(double timeS){
		double t;
		if(getDirection() == 0 || getDirection() == Math.PI/2){
			throw new IllegalStateException("Not able to jump.");
		}
		
		t = timeS;
		double[] pos = new double[]{getX(), getY()};
		while(!getWorld().isImpassable(pos[0], pos[1], getRadius())){
			pos = jumpStep(t);
			t = t + timeS;
		}
		return t;
	}
	
	/**
	 * Method to calculate the position an object would be after performing a jump.
	 * 
	 * @param 	time
	 * 			The time the jump is executed for.
	 * @return	2 Dimensional array containing the position.
	 */
	
	public double[] jumpStep(double time){
		if(getDirection() == 0 || getDirection() == Math.PI/2){
			return new double[]{getX(), getY()};
		}
		double[] positionPerTime = new double[2];
		double v0 = ((getForce()/getMass())*0.5);
		double v0x = (v0*Math.cos(getDirection()));
		double v0y = (v0*Math.sin(getDirection()));
		positionPerTime[0] = (getX()+(v0x*time));
		positionPerTime[1] = (getY()+((v0y*time)-((9.80665*time*time/2))));
		
		return positionPerTime;
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
		if(getDirection() == 0 || getDirection() == Math.PI || getDirection() == 2*Math.PI || getDirection() == Math.PI/2){
			throw new IllegalStateException("Can't jump at the current angle!");
		}
		setX(jumpStep(this.jumpTime(timeS))[0]);
		setY(jumpStep(this.jumpTime(timeS))[1]);
		setActionPoints(0);
		eat();
		fall();
		eat();
	}

}
