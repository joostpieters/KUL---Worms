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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY(Worm worm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOrientation(Worm worm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRadius(Worm worm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRadius(Worm worm, double newRadius) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getActionPoints(Worm worm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName(Worm worm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rename(Worm worm, String newName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getMass(Worm worm) {
		// TODO Auto-generated method stub
		return 0;
	}

}
