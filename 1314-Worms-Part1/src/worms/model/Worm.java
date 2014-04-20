package worms.model;

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

public class Worm {
	
	private double x = 0;
	private double y = 0;
	private double direction;
	private double radius = 0.25;
	private double mass = 0;
	private String name = " ";
	private static double minimalRadius = 0.25;
	private int actionPoints = 0;
	private int hitPoints; 

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
	 * @effect	The new y-coordinate will equal y.
	 * @effect	The new orientation will equal the direction.
	 * @effect 	The new radius will equal the radius.
	 * @effect	The new name will equal the name.
	 * @effect	The new amount of actionpoints will equal the maximum amount of actionpoints.
	 * @throws 	IllegalNameException The given name is not according to prereqs.
	 * @throws	IllegalRadiusException The given radius is not valid.
	 */
	
	@Raw
	public Worm(double x, double y, double direction, double radius, String name) throws IllegalRadiusException, IllegalNameException{
	
		setX(x);
		setY(y);
		setOrientation(direction);
		if(!isValidName(name)){
			throw new IllegalNameException(name);
		}
		rename(name);
		if(!isValidRadius(radius)){
			throw new IllegalRadiusException(radius);
		}
		setRadius(radius);
		setActionPoints(getMaxActionPoints());
		setHitPoints(getMaxHitPoints());

	}
	
	/**
	 * Change the X coordinate of a worm
	 * 
	 * @param	x
	 * 			The new X coordinate.
	 * 
	 */
	
	@Raw
	public void setX(double x){
		this.x = x;
	}
	
	/**
	 * Change the Y coordinate of a worm
	 * 
	 * @param 	y
	 * 			The new Y coordinate.
	 */
	
	@Raw
	public void setY(double y){
		this.y = y;
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
	public void setRadius(double newRadius) throws IllegalRadiusException{
		if(!isValidRadius(newRadius)){
			throw new IllegalRadiusException(radius);
		}
		this.radius = newRadius;
		calcMass();
	}
	
	

	/**
	 * Calculate and set the mass for this worm.
	 * 
	 * @post	The mass will equal the newly calculated mass.
	 * 		  |	new.mass == getMass();
	 */
	
	public void calcMass(){
		mass = (1062*((4*Math.PI*Math.pow(getRadius(), 3))/3));
	}
	
	/**
	 * Return the mass of a worm in kilograms.
	 * 
	 * @return	The current mass of given worm.
	 */
	
	@Basic @Raw
	public double getMass(){
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
	public double getMinimalRadius(){
		return minimalRadius;
	}
	
	/**
	 * 
	 * @param rad
	 * @return	True if the radius is bigger than the minimal radius.
	 * 		  |	result == rad>=getMinimalRadius();
	 */
	
	@Raw
	public static boolean isValidRadius(double rad){
		return (rad >= minimalRadius);
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
		this.direction = direction;
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
	 * Return the X-coordinate of a worm.
	 * 
	 * @return 	The X-position of given worm.
	 */
	
	@Basic
	public double getX(){
		return x;
	}
	
	/**
	 * Return the Y-coordinate of a worm.
	 * 
	 * @return	The Y-position of given worm.
	 */
	
	@Basic
	public double getY(){
		return y;
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
	
	public void rename(String newName) throws IllegalNameException{
			if(isValidName(newName)){
				name = newName;
			}
			else{
				throw new IllegalNameException(name);
			}
		
	}
	
	/**
	 * Check whether a worm is able to move.
	 * 
	 * @param 	steps
	 * 			The amount of steps the worm want to make
	 * @return	True if the amount of steps times the cost each step takes is smaller than the available actionpoints.
	 * 		  |	result == ((steps*stepCost) <= actionPoints)
	 */
	
	@Raw
	public boolean canMove(int steps){
		boolean canMove;
		int stepCost = (int)(Math.abs(Math.cos(getOrientation()))+(Math.abs(Math.sin(getOrientation())*4)));
		if((steps*stepCost) > getActionPoints()){
			canMove = false;
		}
		else{
			canMove = true;
		}
		return canMove;
	}
	
	/**
	 * Make a worm move a certain amount of steps conform to his current orientation.
	 * 
	 * @param 	steps
	 * 			The amount of steps given worm is going to move.
	 * @post	The x and y coordinates will have changed according to the number of steps.
	 * @throws 	IllegalArgumentException() The number of steps is invalid.
	 * 		  |	!canMove(steps);
	 */
	
	public void move(int steps) throws IllegalArgumentException{
		if(!canMove(steps)){
			throw new IllegalArgumentException("The number of steps is invalid");
		}
		
		if(canMove(steps)){
			int stepCost = (int)(Math.abs(Math.cos(getOrientation()))+(Math.abs(Math.sin(getOrientation())*4)));
			setActionPoints(getActionPoints() - (steps*stepCost));
			setX(getX() + (steps*Math.cos(getOrientation())*getRadius()));
			setY(getY() + (steps*Math.sin(getOrientation())*getRadius()));
		}


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
		if(actionPoints < 0 || actionPoints > getMaxActionPoints()){
			return;
		}
		this.actionPoints = actionPoints;
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
		if(hitPoints < 0 || hitPoints > getMaxHitPoints()){
			return;
		}
		this.hitPoints = hitPoints;
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
	
	public double getJumpTime(){
		double initialVelocity = ((getJumpForce()*0.5)/getMass());
		double distance = (((initialVelocity*initialVelocity)*Math.sin(2*getOrientation()))/9.80665);
		return distance/(initialVelocity*Math.cos(getOrientation()));
	}
	
	/**
	 * Return the X and Y coordinates of a worm performing a jump at a given time.
	 * 
	 * @param 	time
	 * 			The time at we would like to check the position of the worm in flight.
	 * @return	The X and Y coordinates of given worm at given time, neatly ordened in an array.
	 * 		  | result == positionPerTime[]
	 * @throws IllegalAPException(getActionPoints())	Invalid amount of actionpoints left.
	 * @throws IllegalDirectionException(getOrientation())	Invalid direction.
	 */
	
	public double[] getJumpStep(double time) throws IllegalAPException, IllegalDirectionException{
		if(!canJump()){
			throw new IllegalAPException(getActionPoints());
		}
		if(!isValidDirection(getOrientation())){
			throw new IllegalDirectionException(getOrientation());
			}
	
		
		double[] positionPerTime = new double[2];
		double initialXVelocity = (((getJumpForce()*0.5)/getMass())*Math.cos(getOrientation()));
		double initialYVelocity = (((getJumpForce()*0.5)/getMass())*Math.sin(getOrientation()));
		positionPerTime[0] = (getX()+(initialXVelocity*time));
		positionPerTime[1] = (getY()+((initialYVelocity*time)-((9.80665*time*time/2))));
		return positionPerTime;
	}
	
	/**
	 * Make a worm jump in a physical trajectory according to gravitation.
	 * 
	 * @post 	The worm will have a new position according to the jumpforce and actionpoints.	
	 * @throws IllegalAPException(getActionPoints())	Invalid amount of actionpoints left.
	 * @throws IllegalDirectionException(getOrientation())	Invalid direction.
	 */
	
	public void jump() throws IllegalAPException, IllegalDirectionException{
		if(!canJump()){
			throw new IllegalAPException(getActionPoints());
		}
		if(!isValidDirection(getOrientation())){
			throw new IllegalDirectionException(getOrientation());
		}
		
		double initialVelocity = ((getJumpForce()*0.5)/getMass());
		double distance = (((initialVelocity*initialVelocity)*Math.sin(2*getOrientation()))/9.80665);
		double placeboDirection = getOrientation();
		
		if(placeboDirection > Math.PI){
			placeboDirection = placeboDirection - (2*Math.PI);
		}
		else if(placeboDirection < -Math.PI){
			placeboDirection = placeboDirection + (2*Math.PI);
		}
		
		while(canJump() == true){
		if(placeboDirection>=0 && placeboDirection<(Math.PI)) {
			setX(getX() + distance);
			setActionPoints(0);
		}
		}
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
}
