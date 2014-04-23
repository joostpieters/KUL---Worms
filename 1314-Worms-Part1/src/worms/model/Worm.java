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
	
	private static String[] names = { "John", "Geoff", "Jennifer", "Philip", "Captain", "Ted", "Lily", "Marshall", "Barney", "Robin", "Zoe", "Frank", "Bill", "McSoap", "Chris", "Sam", "Lau", "Don", "Jim", "Andy", "James O'Hare", "2Slo", "2Pro", "Peter", "Bart", "Sensei", "Robb", "Arya", "Ann", "Ghost", "Solfatare", "Dietrich", "Lars", "Tom", "McAwesome", "Ayden", "Berta", "Daniel", "Matt", "David", "Hamish", "Capaldi", "Romero", "Calvin", "Bondi"};
	private double x = 0;
	private double y = 0;
	private double direction;
	private double radius = 0.25;
	private double mass = 0;
	private World world;
	private String name = " ";
	private static double minimalRadius = 0.25;
	private int actionPoints = 0;
	private int hitPoints;
	private Teams team;
	private boolean removed; 
	private Set<String> weapons = new LinkedHashSet<String>(Arrays.asList(new String[] {"Rifle", "Bazooka"}));
	private Iterator<String> switchWeapon;
	private String weapon;

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
	public Worm(World world, double x, double y, double direction, double radius, String name){
		super(world, x, y, radius);
		setOrientation(direction);
		rename(name);
		setActionPoints(getMaxActionPoints());
		setHitPoints(getMaxHitPoints());
		setTeam(world.getActiveTeam());
		if (getTeam() != null){
			getTeam().addWorm(this);
		}
	}
	
	public Worm(World world) throws Exception{
		this(world, 0.0, 0.0, Math.PI/2, getMinimalRadius(), names[(int)Math.random()*(names.length-1)]);
		if(!world.suitablePos(this)){
			throw new Exception("Worm has not been placed! Cannot find a suitable position.");
		}
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
	public static double getMinimalRadius(){
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
	 * @return	True if the amount of steps times the cost each step takes is smaller than the available actionpoints.
	 * 		  |	result == ((steps*stepCost) <= actionPoints)
	 */
	
	@Raw
	public boolean canMove(){
		if(!isRemoved() && getActionPoints() > 0){
			return true;
		}
		return false;
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
	
	public void move() throws IllegalStateException{
		if(!canMove()){
			throw new IllegalStateException("Unable to move");
		}
		
	
		int stepCost = (int)(Math.abs(Math.cos(getOrientation()))+(Math.abs(Math.sin(getOrientation())*4)));
		setActionPoints(getActionPoints() - (stepCost));
		setX(getBestPos()[0]);
		setY(getBestPos()[1]);
	
	}
	
	private Double[] getBestPos(){
		Double[] bestPos = new Double[]{getX(), getY()};
		double shortestDistance = 0;
		double shortestDiv = -0.7875;
		double div = -0.7875;
		
		while(div <= 0.7875){
			if(getFurthest(getOrientation()+div) != null){
				double furthX = getFurthest(getOrientation()+div)[0];
				double furthY = getFurthest(getOrientation()+div)[1];
				double furthDist = Math.sqrt(Math.pow(getX()-furthX,2)+Math.pow(getY() - furthY,2));
				if(div != 0 || div == 0 && getWorld().isAdjacent(furthX, furthY, getRadius()) && div >= Math.abs(shortestDiv) && furthDist == shortestDistance){
					bestPos[0] = furthX;
					bestPos[1] = furthY;
					shortestDiv = div;
				}
				if(furthDist > shortestDistance){
					bestPos[0] = furthX;
					bestPos[1] = furthY;
					shortestDiv = div;
					shortestDistance = furthDist;
				}
			}
			div = div + 0.0175;
		}
		return bestPos;
	}
	
	public Double[] getFurthest(double angle){
		boolean impFound = false;
		boolean adFound = false;
		Double[] pos = new Double[]{getX()+getRadius()*Math.cos(angle)*0.1, getY()+getRadius()*Math.sin(angle)*0.1};
		if(getWorld().isImpassable(pos[0], pos[1], getRadius()) && !getWorld().isImpassable(getX(),	 getY(), getRadius())){
			impFound = true;
			return null;
		}
		while(!impFound && (Math.sqrt(Math.pow(getX()-pos[0],2)+Math.pow(getY() - pos[1],2))) <= getRadius()){
			pos[0] = pos[0] + 0.05*Math.cos(angle)*getRadius();
			pos[1] = pos[1] + 0.05*Math.sin(angle)*getRadius();
			if(getWorld().isImpassable(pos[0], pos[1], getRadius())){
				impFound = true;
			}
		}
		while(!adFound && impFound && (Math.sqrt(Math.pow(getX()-pos[0],2)+Math.pow(getY() - pos[1],2))) > 0 && !getWorld().objectInWorld(pos[0], pos[1], getRadius())){
			pos[0] = pos[0] - 0.05*Math.cos(angle)*getRadius();
			pos[1] = pos[1] - 0.05*Math.sin(angle)*getRadius();
			if(getWorld().isAdjacent(pos[0], pos[1], getRadius())){
				adFound = true;
				return pos;
			}
		}
		Double[] furthest = new Double[]{getX()+Math.cos(angle)*getRadius(), getY()+Math.sin(angle)*getRadius()};
		if(getWorld().isAdjacent(furthest[0], furthest[1], getRadius()) || angle == getOrientation()){
			return furthest;
		}
		else{
			return null;
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
			newPos = getJumpStep(cnt*timeStep);
			if(getWorld().isImpassable(newPos[0], newPos[1], getRadius())){
				cannotPass = true;
			}
		}
		boolean adPos = false;
		while((!adPos && cannotPass && !getWorld().objectInWorld(newPos[0], newPos[1], getRadius()))){
			cnt--;
			newPos = getJumpStep(cnt*timeStep);			
			if(getWorld().isAdjacent(newPos[0], newPos[1], getRadius())){
				adPos = true;
				return cnt*timeStep;
			}
		}
		return cnt*timeStep;
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
	
	public double[] getJumpStep(double time) throws IllegalArgumentException{
		if(!canJump()){
			throw new IllegalArgumentException();
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
	
	public void jump(double timeStep) throws IllegalStateException{
		if(!canJump()){
			throw new IllegalStateException("can't perform jump :(");
		}
		super.jump(timeStep);
		setActionPoints(0);
		eat();
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
		if(getActionPoints() == 0 || getWorld().isImpassable(getX(), getY(), getRadius())){
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
	
	public boolean canFall(){
		return (!removed() && !getWorld().isImpassable(getX(), getY(), getRadius()) && !getWorld().isAdjacent(getX(), getY(), getRadius()));
	}
	
	public void setWorld(World world){
		this.world = world;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	public void fall() throws IllegalStateException{
		if(!canFall()){
			throw new IllegalStateException("Not able to fall");
		}
		boolean landed = false;
		if(getWorld().isAdjacent(getX(), getY(), getRadius())){
			landed = true;
		}
		double initialY = getY();
		while((!landed) && !getWorld().objectInWorld(getX(), getY(), getRadius()) ){
			double newY = getY()-0.05*getRadius();
			if(getWorld().isAdjacent(getX(), newY, getRadius())){
				this.setY(newY);
				this.setHitPoints((int)(getHitPoints() - (3*(initialY-newY))));
				landed = true;
			}
		}
		if(!landed && (getWorld().objectInWorld(getX(), getY(), getRadius()))){
			remove();
		}
		eat();
	}
	
	public void setTeam(Teams team){
		this.team = team;
	}
	
	public Teams getTeam(){
		return team;
	}
	
	public boolean isRemoved(){
		return removed;
	}

	
	public void selectNextWeapon(){
		if(!switchWeapon.hasNext()){
			switchWeapon = weapons.iterator();
		}
		setWeapon(switchWeapon.next());
	}
	
	public String getWeapon(){
		return weapon;
	}
	
	public void setWeapon(String weapon){
		this.weapon = weapon;
	}
	
	public boolean canShoot(){
		return (!(getActionPoints() == 0) && (!getWorld().isImpassable(getX(), getY(), getRadius()) && !removed()));
	}
	
	public void shoot(int yield){
		if(!canShoot()){
			throw new IllegalStateException("Not able to fire. Get into the bunkers!");
		}
		double shootX = this.getX()+Math.cos(this.getOrientation())*getRadius();
		double shootY = this.getY()+Math.sin(this.getOrientation())*getRadius();
		Projectile proj = null;
		if(getWeapon() == "Bazooka"){
			proj = new Bazooka(getWorld(), shootX, shootY, yield);
		}
		if(getWeapon() == "Rifle"){
			proj = new Rifle(getWorld(), shootX, shootY);
		}
		proj.setCurrentWorm(this);
		setActionPoints(getActionPoints()-proj.getAP());
		getWorld().setActiveProjectile(proj);

	}
	
	public void joinTeam(Teams team){
		team.addWorm(this);
	}

	@Override
	public double getDirection() {
		return getOrientation();
	}

	@Override
	public double getForce() {
		return ((5*getActionPoints()) + (getMass()*9.80665));
	}
	
	public void remove(){
		super.remove();
		try{
			getTeam().removeWorm(this);
		}
		catch(Exception e){
			
		}
	}
	
	public void eat(){
		if(!removed()){
			for(Food food : getWorld().getFood()){
				if(overlaps(food)){
					setRadius(1.1*getRadius());
					food.remove();
				}
			}
		}
	}
}
