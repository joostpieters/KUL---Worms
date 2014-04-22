package worms.model;

public abstract class Projectile extends Jump{
	
	private Worm worm;
	private double radius;
	private final int DENSITY = 7800;

	public Projectile(World world, double xPos, double yPos){
		super(world, xPos, yPos, 0);
		this.setRadius(getRadius());
	}
	
	public void setCurrentWorm(Worm worm){
		if(worm == null){
			throw new IllegalArgumentException("You're trying to add something that isn't a worm as the owner of this object.");
		}
		this.worm = worm;
	}
	
	public Worm currentWorm(){
		return this.worm;
	}
	
	public double getDirection(){
		return this.currentWorm().getOrientation();
	}
	
	public double getRadius(){
		return Math.cbrt((getMass()*0.75)/(DENSITY*Math.PI));
	}
	
	public void shoot(double time){
		super.jump(time);
		doDamage();
		remove();
	}
	
	public void doDamage(){
		if(!removed()){
			for(Worm worm : getWorld().getWorms()){
				if(this.overlaps(worm)){
					worm.setActionPoints(worm.getActionPoints() - getDamage());
				}
			}
		}
	}
	
	public abstract double getMass();
	public abstract double getForce();
	public abstract int getDamage();
	public abstract int getAP();
	
}
