package worms.model;

import worms.model.superclasses.Jump;
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
 * @invar	Each worm must have a valid world.
 * 		 |	getWorld() != null && getWorld.getWorms().contains(this);
 * @invar	Hitpoints must always be less than the maximum hitpoints.
 * 		 | 	getHitPoints() <= getMaxHitPoints();	 
 * @invar	Actionpoints must always be less than the maximum hitpoints.
 * 		 | 	getActionPoints() <= getMaxActionPoints();	
 * @invar	The worm must have a valid weapon.
 * 		 |	isValidWeapon(getCurrentWeapon());
 * 
 * @version 3.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

public class Worm extends Jump {
	
	/**
	 * All of the variables and constants used in this class.
	 */
	
	private static String[] names = { "Podrick", "Cartman", "Kenny", "KEVIN", "Towly", "Kris", "Reinout", "Jorn", "Cedric", "Pjotr", "Vanessa", "Doctor", "Karen Love", "John", "Geoff", "Jennifer", "Philip", "Captain", "Ted", "Lily", "Marshall", "Barney", "Robin", "Zoe", "Frank", "Bill", "McSoap", "Chris", "Sam", "Lau", "Don", "Jim", "Andy", "James O'Hare", "TwoSlo", "TwoPro", "Peter", "Bart", "Sensei", "Robb", "Arya", "Ann", "Ghost", "Solfatare", "Dietrich", "Lars", "Tom", "McAwesome", "Ayden", "Berta", "Daniel", "Matt", "David", "Hamish", "Capaldi", "Romero", "Calvin", "Bondi"};
	private double direction;
	private double radius = 0.25;
	private double mass = 0;
	private String name = " ";
	private static double minimalRadius = 0.25;
	private int actionPoints = 0;
	private int hitPoints;
	private Teams team;
	private String weapon;
	private Program program;
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
	 * @param	world
	 * 			The world that this worm will belong to.
	 * @param	prg
	 * 			The program this worm will run.
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
	 * @effect	The worm's world will be set to the parameter world.
	 * 		  | super(world, x, y, radius);
	 */
	
	@Raw
	public Worm(World world, double x, double y, double direction, double radius, String name, Program prg){
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
		setProgram(prg);
		if(prg != null){
			prg.addWorm(this);
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
	 * @param	prg
	 * 			The program that this worm will run.
	 * @effect	A worm will be created with a radius equal to the minimal radius and a random name from the nameslist.
	 * 		  |	this(world, x, y, Math.PI/2, getMinimalRadius(), names[(int)Math.floor(Math.random()*names.length)]);
	 */
	
	@Raw
	public Worm(World world, double x, double y, Program prg){
			this(world, x, y, Math.PI/2, getMinimalRadius(), names[(int)Math.floor(Math.random()*names.length)], prg);
	}
	
	/**
	 * Check whether a name is conform to given standards
	 * 
	 * @param 	name
	 * 			The string to check.
	 * @return	True if the name contains only letters, spaces and quotationmarks. Must start with a capital and be atleast 2 chars long.
	 * 		  |	if(name.length() < 2 || !validFirstLetter.contains(name.subSequence(0, 1))){
	 * 		  |		return false;}
	 * 		  |	for(int i = 1; i < lenght; i++){
	 *		  |		if(!validLetters.contains(name.subSequence(i, i + 1))){
	 *		  |			validName = false;
	 *		  |			i = lenght;}}
	 */
	
	@Raw
	public static boolean isValidName(String name){
		String validLetters = "\"\' abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String validFirstLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		boolean validName = true;
		int lenght = name.length();
		if(lenght < 2 || !validFirstLetter.contains(name.subSequence(0, 1))){
			validName = false;
		}
		for(int i = 1; i < lenght; i++){
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
	 * 		  | getMaxActionPoints();
	 * @throws	IllegalRadiusException	The given newRadius is not valid.
	 * 		  |	result == !isValidRadius;
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
	 * 		  |	new.mass == (DENSITY*((4.0*Math.PI*(this.getRadius()*this.getRadius()*this.getRadius())/3.0)));
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
	
	@Basic 
	public int getHitPoints(){
		return hitPoints;
	}
	
	/**
	 * Return the current actionpoints of a worm that are left.
	 * 
	 * @return	The current number of actionpoints given worm has.
	 */
	
	@Basic
	public int getActionPoints(){
		return actionPoints;
	}
	
	/**
	 * Give a worm a new name that has to abide certain standards.
	 *  
	 * @param 	newName
	 * 			The new name we would like to give to given worm.
	 * @effect	The new name of this worm will be set to the parameter newName.
	 * 		  |	new.getName() == newName;
	 * @throws	IllegalArgumentException	The given name is not up to standards and thus invalid;
	 * 		  |	!isValidName(newName);
	 */
	
	@Raw
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
	 * @post	The x and y coordinates will have changed according to the number of steps.
	 * @effect	The worm's position and actionpoints will be updated. And the worm will eat.
	 * 		  | setX(newX);
			  |	setY(newY);
			  |	setActionPoints(newAP);
			  |	fall();
			  |	eat();
	 * @throws 	IllegalStateException() Cannot move this way.
	 * 		  |	!canMove;
	 */
		
		public void move() throws IllegalStateException{
			if(!canMove()){
				throw new IllegalStateException("canMove returned false");
			}
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
			if (!(newLocation == null) && !getWorld().isImpassable(newLocation[0], newLocation[1], getRadius())){
				double newX = newLocation[0];
				double newY = newLocation[1];
				double slope = Math.atan((getY() - newY)/(getX() - newX));
				int newAP = (int) Math.round(getActionPoints() - (Math.abs(Math.cos(slope)) + Math.abs(4*Math.sin(slope))));
				if (newAP >= 0){
					setX(newX);
					setY(newY);
					setActionPoints(newAP);
					fall();
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
				//System.out.println("Distance: "+distance + " and HP: "+getHitPoints());
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
	 * @effect	The worm will be facing at a new angle consisting of the previous angle
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
	
	public double getJumpForce(){
		return ((5.0*getActionPoints())+(getMass()*9.80665));
	}
	
	/**
	 * Method to return the initial velocity of a worm.
	 * 
	 * @return 	The initial velocity.
	 * 		  | (getJumpForce()/getMass() * 0.5);
	 */
	
	private double getInitialVelocity(){
		return (getJumpForce()/getMass() * 0.5);
	}
	
	/**
	 * Method to calculate the jumpTime for a worm.
	 * 
	 * @param	timeS
	 * 			The timestep.
	 * @return	The time it takes to jump.
	 *		  |	while(!getWorld().isImpassable(dX, dY, getRadius()) && getWorld().objectInWorld(dX, dY, getRadius())){
	 *		  |		time = time + timeS;
	 *		  |		temp = jumpStep(time);
	 *		  |		dX = temp[0];
	 *		  |		dY = temp[1];
	 *		  |	}
	 *		  |	return time;
	 */
	
	public double jumpTime(double timeS){
		double time = (getRadius()/getInitialVelocity());
		double[] temp = jumpStep(time);
		double dX = temp[0];
		double dY = temp[1];
		while(!getWorld().isImpassable(dX, dY, getRadius()) && getWorld().objectInWorld(dX, dY, getRadius())){
			time = time + timeS;
			temp = jumpStep(time);
			dX = temp[0];
			dY = temp[1];
		}
		return time;
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
		return(getActionPoints() > 0);
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
		return (!(getActionPoints() == 0)); //add removed
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
	
	public void shoot(int yield) throws IllegalStateException{
		if(!canShoot()){
			throw new IllegalStateException("Not able to fire. Get into the bunkers!");
		}
		double shootX = this.getX()+Math.cos(this.getOrientation())*getRadius();
		double shootY = this.getY()+Math.sin(this.getOrientation())*getRadius();
		if(getWeapon() == "Bazooka"){
			if(getActionPoints() < 50){
				throw new IllegalStateException("Not enough AP");
			}
			double F = 	2.5+((yield*(9.5-2.5))/100);
			Projectile proj = new Projectile(getWorld(), shootX, shootY, getDirection(), 0.30, F, yield, 80);
			proj.setCurrentWorm(this);
			getWorld().setActiveProjectile(proj);
			setActionPoints(getActionPoints()-50);

		}
		if(getWeapon() == "Rifle"){
			if(getActionPoints() < 10){
				throw new IllegalStateException("Not enough AP");
			}
			Projectile proj = new Projectile(getWorld(), shootX, shootY, getDirection(), 0.01, 1.5, yield, 20);
			proj.setCurrentWorm(this);
			getWorld().setActiveProjectile(proj);
			setActionPoints(getActionPoints()-10);
		}

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
				//System.out.println("ATE"); -- Debug
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
		double vX = v0 * Math.cos(getOrientation());
		double vY = v0 * Math.sin(getOrientation());
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
		if(!canJump()){
			throw new IllegalStateException("Unable to jump");
		}
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
			setHitPoints(0);
			this.remove();
		}
		else{
			setX(dX);
			setY(dY);
			setActionPoints(0);
			eat();
		}
	}
	
	/**
	 * Method to set a program for a worm.
	 * 
	 * @param 	prg
	 * 			The program that is going to be executed by this worm.
	 */
	
	public void setProgram(Program prg){
		program = prg;
	}
	
	/**
	 * Method to return the program currently being executed by a worm.
	 * 
	 * @return	The program
	 */
	
	public Program getProgram(){
		return program;
	}
	
	/**
	 * Check whether this worm currently has an active program.
	 * 
	 * @return	True if there is a program active, false otherwise.
	 * 		  |	getProgram != null;
	 */
	
	public boolean hasActiveProgram(){
		return (this.program != null);
	}
	
	/**
	 * Method to start the execution of this worm's program.
	 * 
	 * @effect	The worm will act as defined in the program.
	 * 		  |	getProgram().execute();
	 * 
	 */
	
	public void executeProgram(){
		getProgram().execute();
	}

}
