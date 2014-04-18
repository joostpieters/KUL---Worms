package worms.model;

import java.util.ArrayList;

public class Food {

	private ArrayList<Food> collection;
	private double x, y;
	private final double setRadius = 0.20;
	
	public Food(World world, double x, double y){
		if(!validPos(x) || !validPos(y)){
			throw new IllegalArgumentException();
		}
		this.x = x;
		this.y = y;
		world.setFood(this);
		collection.add(this);
		
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
		return setRadius;
	}
	
	public ArrayList<Food> getFoods(){
		return collection;
	}
	
	public Food getFoodIndex(int alt){
		return collection.get(alt);
	}


}
