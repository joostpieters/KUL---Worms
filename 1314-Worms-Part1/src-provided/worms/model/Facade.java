package worms.model;

/**
 * 
 * @author Kristof Achten <kristof.achten@student.kuleuven.be>
 * @version 0.9
 *
 */

public class Facade implements IFacade {

	@Override
	public Worm createWorm(double x, double y, double direction, double radius, String name) {
		Worm newWorm = new Worm(x, y, direction, radius, name);
		return newWorm;
	}

	@Override
	public boolean canMove(Worm worm, int nbSteps) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void move(Worm worm, int nbSteps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void turn(Worm worm, double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump(Worm worm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getJumpTime(Worm worm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
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
