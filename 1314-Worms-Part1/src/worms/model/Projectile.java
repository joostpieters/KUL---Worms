package worms.model;

public class Projectile {
	
	private double time, x, y, mass, force, radius;
	private boolean removed;
	World world;
	Worm current = world.currentWorm();
	
	public Projectile(World world, double x, double y){
		this.x = x;
		this.y = y;
		this.world = world;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	public double getRadius(){
		return Math.pow(this.getMass()/(7800*(4.0/3.0)*Math.PI), 1.0/3.0);
	}
	
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setMass(double mass){
		this.mass = mass;
	}
	
	public double getMass(){
		return this.mass;
	}
	
	public double getForce(){
		return force;
	}
	
	public void setForce(double force){
		this.force = force;
	}
	
	public void shootBaz(double yield){
		Projectile proj = new Projectile(getWorld(), getRadius(), getRadius());
		proj.setMass(300);
		proj.setForce(2.5 + 0.07 * yield);
		shoot();
	}
	
	public void shootRifle(double yield){
		Projectile proj = new Projectile(getWorld(), getRadius(), getRadius());
		proj.setMass(10);
		proj.setForce(1.5);
		shoot();
	}

	public double[] JumpStep(double time){
		double[] jumpStep = new double[2];
		double initialXVel = (((getForce()*0.5)/getMass())*Math.cos(current.getOrientation()));
		double initialYVel = (((getForce()*0.5)/getMass())*Math.sin(current.getOrientation()));
		jumpStep[0] = (getX()+(initialXVel*time));
		jumpStep[1] = (getY()+((initialYVel*time)-((9.80665*time*time/2))));
		return jumpStep;
	}
	
	public void shoot(){
		while(world.isImpassable(current.getX(), current.getY(), current.getRadius())){
			double[] newPositions = JumpStep(jumpTime(getTime()));
			this.setX(newPositions[0]);
			this.setY(newPositions[1]);
		}
	}
	
	private double getTime(){
		return time;
	}
	
	public void setTime(double time){
		this.time = time;
	}
	
	public double jumpTime(double time){
		double initialVelocity = ((getForce()*0.5)/getMass());
		double distance = (((initialVelocity*initialVelocity)*Math.sin(2*current.getOrientation()))/9.80665);
		time = distance/(initialVelocity*Math.cos(current.getOrientation()));
		setTime(time);
		return getTime();
	}
	
	public boolean isActive(){
		if(world.isImpassable(current.getX(), current.getY(), this.getRadius())){
			return true;
		}
		return false;
	}
	
	public void remove(){
		if(!isRemoved()){
			World thisWorld = getWorld();
			if(thisWorld.getProjectile().contains(this)){
				thisWorld.getProjectile().remove(this);
				this.removed = true;
			}
			this.world = null;
		}
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
}
