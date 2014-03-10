package worms.model;

/**
 * 
 * @author Kristof Achten <kristof.achten@student.kuleuven.be>
 * @version 0.9
 *
 */

public class Worm {
	
	public int actionPoints, InitialActionPoints;
	public double xPos, yPos, initialDirection, initialRadius, initialMass, mass;
	public String initialName;
	
	public Worm(double x, double y, double direction, double radius, String name){
	
		xPos = x;
		yPos = y;
		initialDirection = direction;
		initialName = name;		
		if(initialRadius<0.25){
			initialRadius = 0.25;
		}
		else{initialRadius = radius;}
	}
	
	public double getMass(Worm worm){
		mass = 1062*((4/3)*Math.PI*Math.pow(initialRadius, 3));
		return mass;
	}
	
	public double getRadius(Worm worm){
		return initialRadius;
	}
	
	public double getMinimalRadius(Worm worm){
		return 0.25;
	}
	
	public void setRadius(Worm worm, double newRadius){
		initialRadius = newRadius;
	}
	
	public double getOrientation(Worm worm){
		return initialDirection;
	}
	
	public double getX(Worm worm){
		return xPos;
	}
	
	public double getY(Worm worm){
		return yPos;
	}
	
	public String getName(Worm worm){
		return initialName;
	}
	
	public int getMaxActionPoints(Worm worm){
		InitialActionPoints = (int)(worm.getMass(worm));
		actionPoints = InitialActionPoints;
		return InitialActionPoints;
	}
	
	public int getActionPoints(Worm worm){
		return actionPoints;
	}
	
	

}
