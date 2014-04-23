package worms.model;

import worms.model.superclasses.Jump;

public abstract class Projectile extends Jump{
	
	private Worm worm;
	private final int DENSITY = 7800;

	public Projectile(World world, double xPos, double yPos){
		super(world, xPos, yPos, 0.0);
		this.setRadius(getRadius());
	}
	
	public void setCurrentWorm(Worm worm){
		if(worm == null){
			throw new IllegalArgumentException("The worm you're trying to set as owner is null.");
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
		return Math.cbrt((this.getMass()*0.75)/(DENSITY*Math.PI));
	}
	
	public void shoot(double time){
		super.jump(time);
		damage();
		remove();
	}
	
	public void damage(){
		if(!removed()){
			for(Worm worm : this.getWorld().getWorms()){
				if(this.overlaps(worm)){
					worm.setActionPoints(worm.getHitPoints() - this.getDamage());
				}
			}
		}
	}
	
	public abstract double getMass();
	public abstract double getForce();
	public abstract int getDamage();
	public abstract int getAP();
	
}
