package worms.model;

/**
 * Class used to implement the IFacade-class.
 * 
 * @version 1.0
 * @author Kristof Achten <kristof.achten@student.kuleuven.be>
 *
 */

public class Facade implements IFacade {

	public Facade(){
		//This is the default constructor
	}
	
	@Override
	public Worm createWorm(double x, double y, double direction, double radius, String name) throws IllegalArgumentException{
		try {
			Worm newWorm = new Worm(x, y, direction, radius, name);
			return newWorm;
			}
		catch(IllegalArgumentException e){
			throw new ModelException("Unable to create this worm with given arguments.");
		}
	}

	@Override
	public boolean canMove(Worm worm, int nbSteps) {
		return Worm.canMove(worm, nbSteps);
	}

	@Override
	public void move(Worm worm, int nbSteps) {
		Worm.move(worm, nbSteps);
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		return Worm.canTurn(worm, angle);
	}

	@Override
	public void turn(Worm worm, double angle) {
		Worm.turn(worm, angle);
	}

	@Override
	public void jump(Worm worm) {
		Worm.jump(worm);
	}

	@Override
	public double getJumpTime(Worm worm) {
		return Worm.getJumpTime(worm);
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		return Worm.getJumpStep(worm, t);
	}

	@Override
	public double getX(Worm worm) {
		return Worm.getX(worm);
	}

	@Override
	public double getY(Worm worm) {
		return Worm.getY(worm);
	}

	@Override
	public double getOrientation(Worm worm) {
		return Worm.getOrientation(worm);
	}

	@Override
	public double getRadius(Worm worm) {
		return Worm.getRadius(worm);
	}

	@Override
	public void setRadius(Worm worm, double newRadius) {
		Worm.setRadius(worm, newRadius);
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		return Worm.getMinimalRadius(worm);
	}

	@Override
	public int getActionPoints(Worm worm) {
		return Worm.getActionPoints(worm);
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return Worm.getMaxActionPoints(worm);
	}

	@Override
	public String getName(Worm worm) {
		return Worm.getName(worm);
	}

	@Override
	public void rename(Worm worm, String newName) {
		Worm.rename(worm, newName);
	}

	@Override
	public double getMass(Worm worm) {
		return Worm.getMass(worm);
	}

}
