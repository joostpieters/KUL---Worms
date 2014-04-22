package worms.model;

public class Bazooka extends Projectile {
	
	private final int APCOST = 50;
	private int yield;
	private final double MASS = 0.30;
	private final double MINIMALFORCE = 2.5;
	private final double MAXIMALFORCE = 9.5;
	private final int MAXIMALYIELD = 100;
	private final int DAMAGE = 80;
	
	public Bazooka(World world, double xPos, double yPos, int yield){
		super(world, xPos, yPos);
		this.yield = yield;
	}
	
	public int getAP(){
		return APCOST;
	}
	
	public double getMass(){
		return MASS;
	}
	
	public int getYield(){
		return yield;
	}
	
	public boolean validYield(int yield){
		if(yield >= 0 && yield <= MAXIMALYIELD){
			return true;
		}
		return false;
	}
	
	public int getDamage(){
		return DAMAGE;
	}
	
	public double getForce(){
		double F = MINIMALFORCE + ((yield*(MAXIMALFORCE - MINIMALFORCE))/100);
		return F;
	}

}
