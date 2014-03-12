package worms.model;

/**
 * 
 * @author Kristof Achten <kristof.achten@student.kuleuven.be>
 * @version 0.9
 *
 */

public class Worm {
	
	public static int actionPoints;
	public static int InitialActionPoints;
	public static double xPos, yPos, initialDirection;
	public static double initialRadius;
	public static double initialMass;
	public static double mass;
	public static String initialName;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 * @param radius
	 * @param name
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
		if(initialRadius<0.25){
			initialRadius = 0.25;
		}
		else{initialRadius = radius;}
	}
	
	/**
	 * 
	 * @param worm
	 * @return
	 */
	
	public static double getMass(Worm worm){
		mass = 1062*((4/3)*Math.PI*Math.pow(initialRadius, 3));
		return mass;
	}
	
	/**
	 * 
	 * @param worm
	 * @return
	 */
	
	public static double getRadius(Worm worm){
		return initialRadius;
	}
	
	/**
	 * 
	 * @param worm
	 * @return
	 */
	
	public static double getMinimalRadius(Worm worm){
		return 0.25;
	}
	
	/**
	 * 
	 * @param worm
	 * @param newRadius
	 */
	
	public static void setRadius(Worm worm, double newRadius){
		if(newRadius < 0.25){
			initialRadius = 0.25;
		}
		else{initialRadius = newRadius;}
	}
	
	/**
	 * 
	 * @param worm
	 * @return
	 */
	
	public static double getOrientation(Worm worm){
		return initialDirection;
	}
	
	/**
	 * 
	 * @param worm
	 * @return
	 */
	
	public static double getX(Worm worm){
		return xPos;
	}
	
	/**
	 * 
	 * @param worm
	 * @return
	 */
	
	public static double getY(Worm worm){
		return yPos;
	}
	
	/**
	 * 
	 * @param worm
	 * @return
	 */
	
	public static String getName(Worm worm){
		return initialName;
	}
	
	/**
	 * 
	 * @param worm
	 * @return
	 */
	
	@SuppressWarnings("static-access")
	public static int getMaxActionPoints(Worm worm){
		InitialActionPoints = (int)(worm.getMass(worm));
		actionPoints = InitialActionPoints;
		return InitialActionPoints;
	}
	
	/**
	 * 
	 * @param worm
	 * @return
	 */
	
	public static int getActionPoints(Worm worm){
		return actionPoints;
	}
	
	/**
	 * Method to give the current worm a new name. Name may only contain normal letters, capital letters, spaces and quotationmarks. 
	 * @param worm
	 * @param newName
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
	 * 
	 * @param worm
	 * @param steps
	 * @return
	 */
	
	public static boolean canMove(Worm worm, int steps){
		boolean canMove = true;
		int stepCost = (int)(Math.ceil(Math.abs(Math.cos(initialDirection)))+(Math.abs(4*Math.sin(initialDirection))));
		if(stepCost > actionPoints){
			canMove = false;
		}
		return canMove;
	}
	
	/**
	 * 
	 * @param worm
	 * @param steps
	 */
	
	public static void move(Worm worm, int steps){
		//Calc number of possible steps with canMove. True = ++ etc
	}
	
	/**
	 * 
	 * @param worm
	 * @param angle
	 * @return
	 */
	
	public static boolean canTurn(Worm worm, double angle){
		return false;
	}
	

}
