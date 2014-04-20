package worms.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Kristof Achten <kristof.achten@student.kuleuven.be>
 * @version 1.0
 *
 */

public class World {
	
	public ArrayList<Worm> wormsList;
	public ArrayList<Food> foodlist;
	public ArrayList<Teams> teamsList;
	public ArrayList<Projectile> projList;
	Worm worm;
	private int alt;
	private double width;
	private double height;
	private double maxX = Double.MAX_VALUE;
	private double maxY = Double.MAX_VALUE;
	private boolean[][] passable;

	public World(double width, double height, boolean[][] passable, Random random) throws IllegalArgumentException{
		if((!validWidth(width) || !validHeight(height))){
			throw new IllegalArgumentException();
		}
		this.width = width;
		this.height = height;
		this.passable = passable;
		wormsList = new ArrayList<Worm>();
		foodlist = new ArrayList<Food>();
		teamsList = new ArrayList<Teams>();
		projList = new ArrayList<Projectile>();
	}
	
	public boolean validWidth(double xWidth){
		boolean valid = false;
		if(!Double.isNaN(xWidth)){
			if(0.0 <= xWidth && xWidth <= maxX){
				valid = true;
			}
		}
		return valid;
	}
	
	public boolean validHeight(double yHeight){
		boolean valid = false;
		if(!Double.isNaN(yHeight)){
			if(0.0 <= yHeight && yHeight <= maxY){
				valid = true;
			}
		}
		return valid;
	}
	
	public double getWidth(){
		return width;
	}
	
	public void setWidth(double width){
		if(!validWidth(width)){
			throw new IllegalArgumentException();
		}
		this.width = width;
	}
	
	public void setHeight(double height){
		if(!validHeight(height)){
			throw new IllegalArgumentException();
		}
		this.height = height;
	}
	
	public double getHeight(){
		return height;
	}
	
	public void setPassable(boolean[][] passable){
		this.passable = passable;
	}
	
	public boolean getPassable(double x, double y){
		return passable[(int) x][(int) y];
	}
	
	public boolean[][] getPassable(){
		return passable;
	}
	
	/**
	 * could be improved with a for loop that goes about 360°, although not really necessary atm.
	 */
	
	public boolean isImpassable(double x, double y, double radius){
		boolean isImpassable = true;
		if(getPassable(x+radius, y+radius)){
			isImpassable = false;
		}
		if(getPassable(x+radius, y-radius)){
			isImpassable = false;
		}
		if(getPassable(x-radius, y+radius)){
			isImpassable = false;
		}
		if(getPassable(x-radius, y-radius)){
			isImpassable = false;
		}
		
		return isImpassable;
	}
	
	public boolean isAdjacent(double x, double y, double radius){
		double newRadius = radius*0.1;
		return(!isImpassable(x, y, 0) && isImpassable(x, y, newRadius));
	}
	
	public void addWorm(Worm worm){
		wormsList.add(worm);
	}
	
	public void addFood(Food food){
		foodlist.add(food);
	}
	
	public void addTeam(Teams team){
		teamsList.add(team);
	}
	
	public void addProjectile(Projectile proj){
		projList.add(proj);
	}
	
	public ArrayList<Projectile> getProjectile(){
		return projList;
	}
	
	public Worm currentWorm(){
		return wormsList.get(getAlt());
	}
	
	public int getAlt(){
		return alt;
	}
	
	public void setAlt(int alt){
		this.alt = alt;
	}
	
	public ArrayList<Food> getFood(){
		return foodlist;
	}
	
	public ArrayList<Worm> getWorms(){
		return wormsList;
	}
	
	public String getWinner(){
		if(teamsList.size() == 1){
			return teamsList.get(0).getTName();
		}
		else{
			return null;
		}
	}
	
	public boolean isFinished(){
		if(teamsList.size() == 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void nextTurn(){
		this.setAlt(getAlt() + 1);
		worm = currentWorm();
		currentWorm().setActionPoints(currentWorm().getMaxActionPoints());
		currentWorm().setHitPoints(currentWorm().getHitPoints() + 10);
	}

}
