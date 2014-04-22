package worms.model;

public class Rifle extends Projectile {
	
	private final int APCOST = 10;
	private final double MASS = 0.01;
	private final double FORCE = 1.5;
	private final int DAMAGE = 20;
	
	public Rifle(World world, double xPos, double yPos){
		super(world, xPos, yPos);
	}
	
	public int getAP(){
		return APCOST;
	}
	
	public double getMass(){
		return MASS;
	}
	
	
	public int getDamage(){
		return DAMAGE;
	}
	
	public double getForce(){
		return FORCE;
	}

}
