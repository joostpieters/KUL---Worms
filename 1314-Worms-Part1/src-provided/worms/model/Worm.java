package worms.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of worms that can be manipulated, one of the main elements in the game.
 * 
 * @invar	Each worm must have a valid name
 * 		 |	isValidName(name)
 * @invar	Each worm must have a radius bigger than 0.25 metres.
 * 		 |	Worm.radius >= 0.25
 * @invar	The position of each worm must resemble a valid X and Y coordinate.
 * 		 |  isValidPosition(worm)
 * 
 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

public class Worm {
	
	private double x, y, direction, radius, mass;
	private String name;
	private int actionPoints;

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
	 * 		  | isValidName(name)
	 * @pre		The initial radius must be bigger than 0.25 metres.
	 * 		  | radius >= 0.25
	 * @post	If the given name includes characters other than letters, quotationmarks and spaces,
	 * 			a preset name will be given. Otherwise the provided name will have been used.
	 * @post	If the given radius is smaller than 0.25 metres, the radius will be equal 0.25m.
	 * 			Otherwise, the provided radius will have been used.
	 */
	
	@Raw
	public Worm(double x, double y, double direction, double radius, String name){
	
		setX(x);
		setY(y);
		setOrientation(direction);
		rename(name);
		if(radius<=0.25){
			setRadius(0.25);
		}
		else{setRadius(radius);}
		setActionPoints(getMaxActionPoints());

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
	 * 		  | return validLetters.contains(name.subSequence)
	 */
	
	public static boolean isValidName(String name){
		String validLetters = "\"\' abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
	 * Return the mass of a worm in kilograms.
	 * 
	 * @return	The current mass of given worm.
	 * 		  | result == mass	
	 */
	
	@Basic @Raw
	public double getMass(){
		this.mass = (1062*((4*Math.PI*Math.pow(getRadius(), 3))/3));
		return mass ;
	}
	
	/**
	 * Return the radius of a worm in metres.
	 * 
	 * @return	The current radius of given worm.
	 * 	      | result == initialRadius
	 */
	
	@Basic @Raw
	public double getRadius(){
		return radius;
	}
	
	/**
	 * Return the minimal radius a Worm needs to have.
	 * 
	 * @return	The minimal radius of given worm.
	 * 		  | result == 0.25
	 */
	
	@Basic @Immutable @Raw
	public double getMinimalRadius(){
		return 0.25;
	}
	
	/**
	 * Change or set the radius of a Worm to the provided radius.
	 * 
	 * @param 	newRadius
	 * 			The new radius the worm needs to be in metres.
	 * @pre		The new radius of the worm must be at least 0.25 metres.
	 * 	     |	newRadius >= 0.25
	 * @post	If the given radius is smaller than 0.25 metres, the radius will be equal 0.25m.
	 * 			Otherwise, the provided radius will have been used.
	 * @effect	The radius of the worm will be changed.
	 */
	
	public void setRadius(double newRadius){
		if(newRadius <= 0.25){
			this.radius = 0.25;
		}
		else{
			this.radius = newRadius;
			}
	}
	
	/**
	 * Change the direction of a worm.
	 * 
	 * @param	direction
	 * 			The direction we're changing to.
	 * @post	The new direction will be the sum of the old direction and the parameter.
	 */
	
	@Raw
	public void setOrientation(double direction){
		this.direction = direction;
	}
	
	/**
	 * Return the current orientation of a worm.
	 * 
	 * @return	The current direction the worm is oriented at.
	 * 		  | result == initialDirection
	 */
	
	@Raw @Basic
	public double getOrientation(){
		return direction;
	}
	
	/**
	 * Return the X-coordinate of a worm.
	 * 
	 * @return 	The X-position of given worm.
	 * 		  | result == xPos
	 */
	
	@Raw @Basic
	public double getX(){
		return x;
	}
	
	/**
	 * Return the Y-coordinate of a worm.
	 * 
	 * @return	The Y-position of given worm.
	 * 		  | result == yPos
	 */
	
	@Raw @Basic
	public double getY(){
		return y;
	}
	
	/**
	 * Return the name of a worm.
	 * 
	 * @return	The name of given worm.
	 * 		  | result.equals(initialName)
	 */
	
	@Raw @Basic
	public String getName(){
		return name;
	}
	
	/**
	 * Return the maximum of actionpoints a worm can have.
	 * 
	 * @return 	The maximum actionpoints given worm can have.
	 * 		  |	result == initialActionPoints
	 */
	
	@Raw @Basic
	public int getMaxActionPoints(){
		return (int)(getMass());
	}
	
	/**
	 * Return the current actionspoint of a worm that are left.
	 * 
	 * @return	The current number of actionpoints given worm has.
	 * 		  |	result == actionPoints
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
	 * @pre		The name must be a valid name.
	 * 		 |	isValidName(newName)
	 * @post	If the given name includes characters other than letters, quotationmarks and spaces,
	 * 			a preset name will be given. Otherwise the provided name will have been used.
	 * @effect	The worm will be given a nem name.
	 */
	
	public void rename(String newName) throws IllegalArgumentException{
			if(isValidName(newName)){
				name = newName;
			}
			else{
				throw new IllegalArgumentException("Name contains invalid chararcters");
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
	
	@Basic
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
	 * @pre		You must have a sufficient amount of actionpoints
	 * 		  |	canMove = true;
	 * @effect	The worm will move given amount of steps.
	 */
	
	public void move(int steps) throws IllegalArgumentException{
		try{
		if(canMove(steps)){
			int stepCost = (int)(Math.abs(Math.cos(getOrientation()))+(Math.abs(Math.sin(getOrientation())*4)));
			setActionPoints(getActionPoints() - (steps*stepCost));
			setX(getX() + (steps*Math.cos(getOrientation())*getRadius()));
			setY(getY() + (steps*Math.sin(getOrientation())*getRadius()));
		}
		else{
			throw new IllegalArgumentException("You do not have enough actionpoints left");
		}
		}
		catch(Exception e){
			throw new IllegalArgumentException("This is an illegal move/input");
		}
	}
	
	/**
	 * Change the Actionpoints of a worm.
	 * 
	 * @param 	actionPoints
	 * 			The new amount of actionpoints.
	 * @post	The actionpoints provided in the parameter will equal the new amount of actionPoints.
	 */
	
	@Basic @Raw
	public void setActionPoints(int actionPoints){
		this.actionPoints = actionPoints;
	}
	
	/**
	 * Check whether a worm is able to turn.
	 * 
	 * @param 	angle
	 * 			The angle we would like to turn given worm with.
	 * @return	True if the cost of the turn is smaller than the actionpoints that remain.
	 * 		  |	result == (turnCost <= actionPoints)
	 */
	
	@Basic
	public boolean canTurn(double angle){
		boolean canTurn = true;
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
	 * @effect	The worm will turn given angle.
	 * @post	The worm will be facing at a new angle consisting of the previous angle
	 * 			with the angle specified as parameter added to it.
	 */
	
	public void turn(double angle){
		if(canTurn(angle)){
		setOrientation(getOrientation()+angle);
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
	
	@Basic @Raw
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
	 */
	
	@Basic @Raw
	public double[] getJumpStep(double time){
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
	 * @pre		The angle at which the worm is going to jump must be a value between 0 and 2 pi.
	 * 		  |	if(getOrientation > Math.PI){
	 * 		  | 	setOrientation(getOrientation() + 2*Math.PI);}
	 * 		  |	else if(getOrientation < Math.PI{
	 * 		  | 	setOrientation(getOrientation() - 2*Math.PI);}	
	 * @effect	The worm will jump.
	 * @post 	The worm will have a new position according to the jumpforce and actionpoints.	
	 */
	
	public void jump() throws IllegalArgumentException{
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

		else{
				throw new IllegalArgumentException("Cannot make a jump a this angle");
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
	
	@Basic @Raw
	public boolean canJump(){
		boolean canJump = true;
		if(getActionPoints() == 0){
			canJump = false;
		}
		return canJump;
	}
}
