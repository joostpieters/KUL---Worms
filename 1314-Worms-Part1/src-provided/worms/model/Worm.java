package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

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
 *
 */

public class Worm {
	
	private static double x, y, direction, radius;
	private static String name;
	private static int actionPoints;

	/**
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
	
	public Worm(double x, double y, double direction, double radius, String name){
	
		Worm.x = x;
		Worm.y = y;
		Worm.direction = direction;
		if(Worm.isValidName(name)){
			Worm.name = name;
		}
		else{
			System.out.println("This name is not correct. The new name may only contain alphabetical letters, spaces and quotationmarks. Your worm will be given a preset name.");
			int i = (int)((Math.random()*7)+1);
			switch(i){		
			case 1:
				Worm.name = "Conor O'Brian";
				break;
			case 2:
				Worm.name = "Cap'n Transalore";
				break;
			case 3:
				Worm.name = "Tom Baker";
				break;
			case 4:
				Worm.name = "David Smith";
				break;
			case 5:
				Worm.name = "Matthew O'Hare";
				break;
			case 6:
				Worm.name = "Procra Stinator";
				break;
			case 7:
				Worm.name = "Jack Harkness";
				break;
			default:
				Worm.name = "Geoff Mermaid";
			}
		}
		if(radius>=0.25){
			Worm.radius = 0.25;
		}
		else{Worm.radius = radius;}
	}
	
	public static boolean isValidName(String name){
		return name.matches("^[a-zA-Z']+$");
	}
	
	/**
	 * Return the mass of a worm in kilograms.
	 * 
	 * @param 	worm
	 * 			The worm of whom the mass needs to be returned.
	 * @return	The current mass of given worm.
	 * 		  | result == mass	
	 */
	
	@Basic
	public static double getMass(Worm worm){	
		return 1062*((4/3)*Math.PI*Math.pow(Worm.radius, 3));
	}
	
	/**
	 * Return the radius of a worm in metres.
	 * 
	 * @param 	worm
	 * 			The worm of whom the radius needs to be returned.
	 * @return	The current radius of given worm.
	 * 	      | result == initialRadius
	 */
	
	@Basic
	public static double getRadius(Worm worm){
		return Worm.radius;
	}
	
	/**
	 * Return the minimal radius a Worm needs to have.
	 * 
	 * @param 	worm
	 * 			The worm of whom the minimal radius needs to be returned.
	 * @return	The minimal radius of given worm.
	 * 		  | result == 0.25
	 */
	
	@Basic @Immutable
	public static double getMinimalRadius(Worm worm){
		return 0.25;
	}
	
	/**
	 * Change or set the radius of a Worm to the provided radius.
	 * 
	 * @param 	worm
	 * 			The worm of whom the radius needs to be set or changed.
	 * @param 	newRadius
	 * 			The new radius the worm needs to be in metres.
	 * @pre		The new radius of the worm must be at least 0.25 metres.
	 * 	     |	newRadius >= 0.25
	 * @post	If the given radius is smaller than 0.25 metres, the radius will be equal 0.25m.
	 * 			Otherwise, the provided radius will have been used.
	 * @effect	The radius of the worm will be changed.
	 */
	
	public static void setRadius(Worm worm, double newRadius){
		if(newRadius < 0.25){
			Worm.setRadius(worm, 0.25);
		}
		else{
			Worm.setRadius(worm, newRadius);
			}
	}
	
	/**
	 * Return the current orientation of a worm.
	 * 
	 * @param 	worm
	 * 			The worm of whom the orientation needs to be returned.
	 * @return	The current direction the worm is oriented at.
	 * 		  | result == initialDirection
	 */
	
	@Basic
	public static double getOrientation(Worm worm){
		return Worm.direction;
	}
	
	/**
	 * Return the X-coordinate of a worm.
	 * 
	 * @param 	worm
	 * 			The worm of whom the X-coordinate needs to be returned.
	 * @return 	The X-position of given worm.
	 * 		  | result == xPos
	 */
	
	@Basic
	public static double getX(Worm worm){
		return Worm.x;
	}
	
	/**
	 * Return the Y-coordinate of a worm.
	 * 
	 * @param 	worm
	 * 			The worm of whom the Y-coordinate needs to be returned.
	 * @return	The Y-position of given worm.
	 * 		  | result == yPos
	 */
	
	@Basic
	public static double getY(Worm worm){
		return Worm.y;
	}
	
	/**
	 * Return the name of a worm.
	 * 
	 * @param 	worm
	 * 			The worm of whom the name needs to be returned.
	 * @return	The name of given worm.
	 * 		  | result.equals(initialName)
	 */
	
	@Basic
	public static String getName(Worm worm){
		return Worm.name;
	}
	
	/**
	 * Return the maximum of actionpoints a worm can have.
	 * 
	 * @param 	worm
	 * 			The worm of whom the maximum actionpoints need to be returned.
	 * @return 	The maximum actionpoints given worm can have.
	 * 		  |	result == initialActionPoints
	 */
	
	@SuppressWarnings("static-access")
	@Basic
	public static int getMaxActionPoints(Worm worm){
		Worm.actionPoints = (int)(worm.getMass(worm));
		return (int)(worm.getMass(worm));
	}
	
	/**
	 * Return the current actionspoint of a worm that are left.
	 * 
	 * @param 	worm
	 *			The worm of whom the actionpoints need to be returned.
	 * @return	The current number of actionpoints given worm has.
	 * 		  |	result == actionPoints
	 */
	
	@Basic
	public static int getActionPoints(Worm worm){
		return Worm.actionPoints;
	}
	
	/**
	 * Give a worm a new name that has to abide certain standards.
	 *  
	 * @param 	worm
	 * 			The worm of whom we want the name to be changed..
	 * @param 	newName
	 * 			The new name we would like to give to given worm.
	 * @pre		The name must be a valid name.
	 * 		 |	isValidName(newName)
	 * @post	If the given name includes characters other than letters, quotationmarks and spaces,
	 * 			a preset name will be given. Otherwise the provided name will have been used.
	 * @effect	The worm will be given a nem name.
	 */
	
	public static void rename(Worm worm, String newName){
			if(Worm.isValidName(newName)){
				Worm.name = newName;
			}
			else{
				System.out.println("This name is not correct. The new name may only contain alphabetical letters, spaces and quotationmarks.");
			}
		
	}
	
	/**
	 * Check whether a worm is able to move.
	 * 
	 * @param 	worm
	 * 			The worm of who we are checking whether he is able to move a certain amount of steps.
	 * @param 	steps
	 * 			The amount of steps the worm want to make
	 * @return	True if the amount of steps times the cost each step takes is smaller than the available actionpoints.
	 * 		  |	result == ((steps*stepCost) <= actionPoints)
	 */
	
	@Basic
	public static boolean canMove(Worm worm, int steps){
		boolean canMove = true;
		int stepCost = (int) ((int)(Math.ceil(Math.abs(Math.cos(Worm.getOrientation(worm)))))+(Math.abs(4*Math.sin(Worm.getOrientation(worm)))));
		if((steps*stepCost) > Worm.getActionPoints(worm)){
			canMove = false;
		}
		return canMove;
	}
	
	/**
	 * Make a worm move a certain amount of steps conform to his current orientation.
	 * 
	 * @param	worm
	 * 			The worm who we are going to move.		
	 * @param 	steps
	 * 			The amount of steps given worm is going to move.
	 * @effect	The worm will move given amount of steps.
	 */
	
	public static void move(Worm worm, int steps){
		try{
		if(Worm.canMove(worm, steps) == true){
			int stepCost = (int)(Math.ceil(Math.abs(Math.cos(Worm.getOrientation(worm))))+(Math.abs(4*Math.sin(Worm.getOrientation(worm)))));
			Worm.actionPoints -= (steps*stepCost);
			Worm.x += (steps*(Math.cos(Worm.getOrientation(worm))*Worm.getRadius(worm)));
			Worm.x += (steps*(Math.sin(Worm.getOrientation(worm))*Worm.getRadius(worm)));
		}
		else{
			System.out.print("You do not have enough actionpoint to make this move.");
		}
		}
		catch(Exception e){
			System.out.print("This is an illegal move.");
		}
	}
	
	/**
	 * Check whether a worm is able to turn.
	 * 
	 * @param 	worm
	 * 			The worm we anticipate on turning.
	 * @param 	angle
	 * 			The angle we would like to turn given worm with.
	 * @return	True if the cost of the turn is smaller than the actionpoints that remain.
	 * 		  |	result == (turnCost <= actionPoints)
	 */
	
	@Basic
	public static boolean canTurn(Worm worm, double angle){
		boolean canTurn = true;
		int turnCost = (int)(Math.ceil(angle*(60/(2*Math.PI))));
		if(turnCost > Worm.getActionPoints(worm)){
			canTurn = false;
		}
		return canTurn;
	}
	
	/**
	 * Make a worm turn by adding or subtracting a certain angle from/to its current direction.
	 * 
	 * @param 	worm
	 * 			The worm who we are going to turn a certain angle.
	 * @param 	angle
	 * 			The angle we would like given worm to turn.
	 * @effect	The worm will turn given angle.
	 */
	
	public static void turn(Worm worm, double angle){
		if(Worm.canTurn(worm, angle)){ //TODO: Add turncost
		Worm.direction += (angle);
		}
	}
	
	/**
	 * Return the Force that's going to be exerted on a worm during a jump.
	 * 
	 * @param 	worm
	 * 			The worm to calculate the force for.
	 * @return	The force that needs to be exerted given said conditions.
	 * 		  |	result == ((5*actionPoints)+(mass*9.80665))
	 */
	
	@Basic
	public static double getJumpForce(Worm worm){
		return ((5*actionPoints)+(Worm.getMass(worm)*9.80665));
	}

	/**
	 * Return the time it will take to potentially make a jump with respect to the worms details.
	 * 
	 * @param 	worm
	 * 			The worm to calculate the potential jump time for.
	 * @return	The time a potential jump will take.
	 * 		  |	result == distance/(initialVelocity*cos(initialDirection))
	 */
	
	@Basic
	public static double getJumpTime(Worm worm){
		double initialVelocity = ((Worm.getJumpForce(worm)*0.5)/Worm.getMass(worm));
		double distance = (((initialVelocity*initialVelocity)*Math.sin(2*Worm.getOrientation(worm)))/9.80665);
		return distance/(initialVelocity*Math.cos(Worm.getOrientation(worm)));
	}
	
	/**
	 * Return the X and Y coordinates of a worm performing a jump at a given time.
	 * 
	 * @param 	worm
	 * 			The worm to calculate the potential jump trajectory for.
	 * @param 	time
	 * 			The time at we would like to check the position of the worm in flight.
	 * @return	The X and Y coordinates of given worm at given time, neatly ordened in an array.
	 * 		  | result == positionPerTime[]
	 */
	
	@Basic
	public static double[] getJumpStep(Worm worm, double time){
		double[] positionPerTime = new double[2];
		double initialXVelocity = (((Worm.getJumpForce(worm)*0.5)/Worm.getMass(worm))*Math.cos(Worm.getOrientation(worm)));
		double initialYVelocity = (((Worm.getJumpForce(worm)*0.5)/Worm.getMass(worm))*Math.sin(Worm.getOrientation(worm)));
		positionPerTime[0] = (Worm.getX(worm)+(initialXVelocity*time));
		positionPerTime[1] = (Worm.getY(worm)+((initialYVelocity*time)-((9.80665*time*time/2))));
		return positionPerTime;
	}
	
	/**
	 * Make a worm jump in a physical trajectory according to gravitation.
	 * 
	 * @param 	worm
	 * 			The worm that is going to jump.
	 * @effect	The worm will have jumped unless he was facing downwards.	
	 */
	
	public static void jump(Worm worm){
		double initialVelocity = ((Worm.getJumpForce(worm)*0.5)/getMass(worm));
		double distance = (((initialVelocity*initialVelocity)*Math.sin(2*Worm.getOrientation(worm)))/9.80665);
		if(Worm.getOrientation(worm)>=0 && Worm.getOrientation(worm)<(Math.PI/2)){
			Worm.x += distance;
		}
		else if(Worm.getOrientation(worm)>(Math.PI/2) && Worm.getOrientation(worm) <= Math.PI){
			Worm.y -= distance;
		}
		else{
			System.out.println("Jumping at this angle was not such a good idea.");
		}
		
		actionPoints = 0;
		
	}
}
