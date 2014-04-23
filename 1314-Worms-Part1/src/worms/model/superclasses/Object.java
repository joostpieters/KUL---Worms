package worms.model.superclasses;

import worms.model.World;

public abstract class Object {
	
	private boolean isRemoved;
	private double x, y, radius;
	private World world;
	

	public Object(World world, double xPos, double yPos, double radius){
		if(!isValidPos(xPos, yPos)){
			throw new IllegalArgumentException("This is not a valid position.");
		}
		this.x = xPos;
		this.y = yPos;
		setRadius(radius);
		setWorld(world);
		if(world.objectInWorld(getX(), getY(), getRadius())){
			this.remove();
		}
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double newX){
		if(!Double.isNaN(newX)){
			this.x = newX;
		}
	}
	
	public void setWorld(World world){
		if(world == null){
			throw new IllegalArgumentException("Not a valid world!");
		}
		if(world != null){
			this.world = world;
			getWorld().addObjects(this);
		}

		
	}
	
	public World getWorld(){
		return this.world;
	}
	
	public void setY(double newY){
		if(!Double.isNaN(newY)){
			this.y = newY;
		}
		if(getWorld().objectInWorld(getX(), getY(), getRadius())){
			this.remove();
		}
	}
	
	public boolean isValidPos(double x, double y){
		return (!Double.isNaN(x) && !Double.isNaN(y));
	}
	
	public double getRadius(){
		return this.radius;
	}
	
	public void setRadius(double radius){
		if(Double.isNaN(radius)){
			throw new IllegalArgumentException("Not a valid radius!");
		}
		this.radius = radius;
	}
	
	public boolean removed(){
		return isRemoved;
	}
	
	public void remove(){
		if(!removed()){
			World ex = this.getWorld();
			this.isRemoved = true;
			if(ex.getObjects().contains(this)){
				ex.getObjects().remove(this);
			}
			this.world = null;	
		}
	}
	
	public boolean overlaps(Object object){
		if((getX() - object.getX()) <= (getRadius() + object.getRadius()) || (getY() - object.getY()) <= (getRadius() + object.getRadius())){
			return true;
		}
		return false;
	}

}
