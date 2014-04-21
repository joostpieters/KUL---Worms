package worms.model;

public class Food {

	private double x, y;
	private boolean removed;
	private World world;
	private final static double RADIUS = 0.20;
	
	public Food(World world, double x, double y){		
		if(!validPos(x) || !validPos(y)){
			throw new IllegalArgumentException();
		}
		this.x = x;
		this.y = y;
		this.world = world;
		if(world.objectInWorld(x, y, RADIUS)){
			this.remove();
		}
		
	}
	
	public void remove(){
		if(!isRemoved()){
			World thisWorld = getWorld();
			if(thisWorld.getFood().contains(this)){
				thisWorld.getFood().remove(this);
				removed = true;
			}
			this.world = null;
		}
	}
	
	public World getWorld(){
		return world;
	}
	
	public boolean validPos(double x){
		return(!Double.isNaN(x));
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		if(!validPos(x)){
			throw new IllegalArgumentException();
		}
		this.x = x;
	}
	
	public void setY(double y){
		if(!validPos(y)){
			throw new IllegalArgumentException();
		}
		this.y = y;
	}
	
	public double getRadius(){
		return RADIUS;
	}

	public boolean isRemoved(){
		return removed;
	}

}
