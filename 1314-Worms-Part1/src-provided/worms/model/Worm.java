package worms.model;

/**
 * A class of worms that can be manipulated, one of the main elements in the game.
 * 
 * @author Kristof Achten <kristof.achten@student.kuleuven.be>
 * @version 0.9
 *
 */

public class Worm {
	
	public static int actionPoints;
	public static int initialActionPoints;
	public static double xPos, yPos, initialDirection;
	public static double initialRadius;
	public static double initialMass;
	public static double mass;
	public static String initialName;
	
	/**
	 * Initialize a ne Worm with given position, direction, radius and name.
	 * 
	 * @param  	x
	 * 		  	The initial X-coördinate of the worm.
	 * @param 	y
	 * 		  	The initial Y-coördinate of the worm.	
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
	 */
	
	public Worm(double x, double y, double direction, double radius, String name){
	
		xPos = x;
		yPos = y;
		initialDirection = direction;
		if(name.matches("^[a-zA-Z']+$")){
			initialName = name;
		}
		else{
			System.out.println("This name is not correct. The new name may only contain alphabetical letters, spaces and quotationmarks. Your worm will be given a preset name.");
			int i = (int)((Math.random()*7)+1);
			switch(i){		
			case 1:
				initialName = "Conor O'Brian";
				break;
			case 2:
				initialName = "Cap'n Transalore";
				break;
			case 3:
				initialName = "Tom Baker";
				break;
			case 4:
				initialName = "David Smith";
				break;
			case 5:
				initialName = "Matthew O'Hare";
				break;
			case 6:
				initialName = "Procra Stinator";
				break;
			case 7:
				initialName = "Jack Harkness";
				break;
			default:
				initialName = "Geoff Mermaid";
			}
		}
		if(initialRadius>=0.25){
			initialRadius = 0.25;
		}
		else{initialRadius = radius;}
	}
	
	/**
	 * Return the mass of a worm in kilograms.
	 * 
	 * @param 	worm
	 * 			The worm of whom the mass needs to be returned.
	 * @return	The current mass of given worm.
	 * 		  | result == mass	
	 */
	
	public static double getMass(Worm worm){
		mass = 1062*((4/3)*Math.PI*Math.pow(initialRadius, 3));
		return mass;
	}
	
	/**
	 * Return the radius of a worm in metres.
	 * 
	 * @param 	worm
	 * 			The worm of whom the radius needs to be returned.
	 * @return	The current radius of given worm.
	 * 	      | result == initialRadius
	 */
	
	public static double getRadius(Worm worm){
		return initialRadius;
	}
	
	/**
	 * Return the minimal radius a Worm needs to have.
	 * 
	 * @param 	worm
	 * 			The worm of whom the minimal radius needs to be returned.
	 * @return	The minimal radius of given worm.
	 * 		  | result == 0.25
	 */
	
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
	 */
	
	public static void setRadius(Worm worm, double newRadius){
		if(newRadius < 0.25){
			initialRadius = 0.25;
		}
		else{initialRadius = newRadius;}
	}
	
	/**
	 * Return the current oriëntation of a worm.
	 * 
	 * @param 	worm
	 * 			The worm of whom the oriëntation needs to be returned.
	 * @return	The current direction the worm is oriënted at.
	 * 		  | result == initialDirection
	 */
	
	public static double getOrientation(Worm worm){
		return initialDirection;
	}
	
	/**
	 * Return the X-coördinate of a worm.
	 * 
	 * @param 	worm
	 * 			The worm of whom the X-coördinate needs to be returned.
	 * @return 	The X-position of given worm.
	 * 		  | result == xPos
	 */
	
	public static double getX(Worm worm){
		return xPos;
	}
	
	/**
	 * Return the Y-coördinate of a worm.
	 * 
	 * @param 	worm
	 * 			The worm of whom the Y-coördinate needs to be returned.
	 * @return	The Y-position of given worm.
	 * 		  | result == yPos
	 */
	
	public static double getY(Worm worm){
		return yPos;
	}
	
	/**
	 * Return the name of a worm.
	 * 
	 * @param 	worm
	 * 			The worm of whom the name needs to be returned.
	 * @return	The name of given worm.
	 * 		  | result.equals(initialName)
	 */
	
	public static String getName(Worm worm){
		return initialName;
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
	public static int getMaxActionPoints(Worm worm){
		initialActionPoints = (int)(worm.getMass(worm));
		actionPoints = initialActionPoints;
		return initialActionPoints;
	}
	
	/**
	 * Return the current actionspoint of a worm that are left.
	 * 
	 * @param 	worm
	 *			The worm of whom the actionpoints need to be returned.
	 * @return	The current number of actionpoints given worm has.
	 * 		  |	result == actionPoints
	 */
	
	public static int getActionPoints(Worm worm){
		return actionPoints;
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
	 */
	
	public static void rename(Worm worm, String newName){
			if(newName.matches("^[a-zA-Z]+$")){
				initialName = newName;
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
	 * 		  |	result == ((steps*stepCost) > actionPoints)
	 */
	
	public static boolean canMove(Worm worm, int steps){
		boolean canMove = true;
		int stepCost = (int)(Math.ceil(Math.abs(Math.cos(initialDirection)))+(Math.abs(4*Math.sin(initialDirection))));
		if((steps*stepCost) > actionPoints){
			canMove = false;
		}
		return canMove;
	}
	
	/**
	 * Make a worm move a certain amount of steps conform to his current oriëntation.
	 * 
	 * @param	worm
	 * 			The worm who we are going to move..		
	 * @param 	steps
	 * 			The amount of steps given worm is going to move.
	 */
	
	public static void move(Worm worm, int steps){
		//Calc number of possible steps with canMove. True = ++ etc
	}
	
	/**
	 * Check whether a worm is able to turn.
	 * 
	 * @param 	worm
	 * 			The worm we anticipate on turning.
	 * @param 	angle
	 * 			The angle we would like to turn given worm with.
	 * @return	True if ...
	 */
	
	public static boolean canTurn(Worm worm, double angle){
		return false;
	}
	

}
