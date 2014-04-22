package worms.model;

public abstract class Jump extends Object{

	public Jump(World world, double xPos, double yPos, double radius){
		super(world, xPos, yPos, radius);
	}
	
	public double[] jumpStep(double time){
		if(getDirection() == 0 || getDirection() == Math.PI || getDirection() == 2*Math.PI || getDirection() == Math.PI/2){
			return new double[]{getX(), getY()};
		}
		double[] positionPerTime = new double[2];
		double v0 = ((getForce()/getMass())*0.5);
		double v0x = (v0*Math.cos(getDirection()));
		double v0y = (v0*Math.sin(getDirection()));
		positionPerTime[0] = (getX()+(v0x*time));
		positionPerTime[1] = (getY()+((v0y*time)-((9.80665*time*time/2))));
		
		return positionPerTime;
	}
	
	public void jump(double timeS){
		if(getDirection() == 0 || getDirection() == Math.PI || getDirection() == 2*Math.PI || getDirection() == Math.PI/2){
			throw new IllegalStateException("Can't jump at the current angle!");
		}
		setX(jumpStep(jumpTime(timeS))[0]);
		setY(jumpStep(jumpTime(timeS))[1]);

		
	}
	
	public double jumpTime(double timeS){
		if(getDirection() == 0 || getDirection() == Math.PI || getDirection() == 2*Math.PI || getDirection() == Math.PI/2){
			return 0; //Worm shouldn't jump at all, that's why we return 0 because that will cancel out jumpstep;
		}
		double curX = getX() + Math.cos(getDirection());
		double curY = getY() + Math.sin(getDirection());
		int cnt = 0;
		boolean found = false;
		if(getWorld().isImpassable(curX, curY, getRadius())){
			found = true;
			return 0;
		}
		while(!found && !getWorld().objectInWorld(curX, curY, getRadius())){
			cnt++;
			double[] newPos = jumpStep(cnt*timeS);
			curX = newPos[1];
			curY = newPos[2];
			if(getWorld().isImpassable(curX, curY, getRadius())){
				found = true;
				return cnt*timeS;
			}
			}
		return 0;
	}
	
	public abstract double getDirection();
	public abstract double getForce();
	public abstract double getMass();
}
