package worms.model;

/**
 * Class used to implement the IFacade-class.
 * 
 * @version 1.0
 * @author Kristof Achten <kristof.achten@student.kuleuven.be>
 *
 */

public class Facade implements IFacade {

	
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
		return worm.canMove(nbSteps);
	}

	@Override
	public void move(Worm worm, int nbSteps) {
		worm.move(nbSteps);
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	}

	@Override
	public void jump(Worm worm){
			worm.jump();
	}

	@Override
	public double getJumpTime(Worm worm) {
		return worm.getJumpTime();
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		return worm.getJumpStep(t);
	}

	@Override
	public double getX(Worm worm) {
		return worm.getX();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getY();
	}

	@Override
	public double getOrientation(Worm worm) {
		return worm.getOrientation();
	}

	@Override
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	@Override
	public void setRadius(Worm worm, double newRadius) {
		worm.setRadius(newRadius);
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		return worm.getMinimalRadius();
	}

	@Override
	public int getActionPoints(Worm worm) {
		return worm.getActionPoints();
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return worm.getMaxActionPoints();
	}

	@Override
	public String getName(Worm worm) {
		return worm.getName();
	}

	@Override
	public void rename(Worm worm, String newName) {
		worm.rename(newName);
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

}
