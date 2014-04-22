package worms.model;

import java.util.ArrayList;
import java.util.Random;

import worms.util.Util;

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
	private final Random random;
	private double width;
	private double height;
	private double maxX = Double.MAX_VALUE;
	private double maxY = Double.MAX_VALUE;
	private boolean[][] passable;
	private Teams activeTeam;
	private Projectile activeProjectile;

	public World(double width, double height, boolean[][] passable, Random random) throws IllegalArgumentException{
		if((!validWidth(width) || !validHeight(height))){
			throw new IllegalArgumentException("Dimensions are not valid!");
		}
		if(random == null){
			throw new IllegalArgumentException("random was null, which is not a valid random");
		}
		this.width = width;
		this.height = height;
		this.passable = passable;
		this.random = random;
		wormsList = new ArrayList<Worm>();
		foodlist = new ArrayList<Food>();
		teamsList = new ArrayList<Teams>();
		projList = new ArrayList<Projectile>();
		setActiveTeam(null);
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
	
	public boolean isImpassable(double x, double y, double radius){

		int altX = (int) (x/getWidth()*getPassable()[0].length);
		int altY = (int) ((1-y/getHeight())*getPassable().length);
		int altRad = (int) Math.abs(radius/getWidth()*getPassable()[0].length);
		
		for(int i = altX-altRad; i < altX+altRad; i++){
			if(i < getPassable()[0].length && i >=0){
				for(int j = altY - altRad; j < altY+altRad; j++){
					if(j < getPassable().length && j >=0){
						if(reachable(Math.abs(i-altX), Math.abs(j-altY), altRad) && !getPassable()[j][i]){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean reachable(int altX, int altY, int altRad){
		return Util.fuzzyLessThanOrEqualTo(altX, altY, altRad);
	}
	
	public boolean objectInWorld(double x, double y, double radius){
		double pos1 = x + radius;
		double pos2 = x - radius;
		double pos3 = y + radius;
		double pos4 = y - radius;
		if(!Util.fuzzyGreaterThanOrEqualTo(pos2, 0) || !Util.fuzzyLessThanOrEqualTo(pos3, getHeight()) || !Util.fuzzyLessThanOrEqualTo(pos1, getWidth()) || Util.fuzzyGreaterThanOrEqualTo(pos4, 0)){
			return false;
		}
		return true;
	}
	
	public boolean isAdjacent(double x, double y, double radius){
		double newRadius = radius*0.1;
		return(!isImpassable(x, y, radius) && isImpassable(x, y, newRadius));
	}
	
	public void addWorm(Worm worm){
		wormsList.add(worm);
	}
	
	public void addFood(Food food){
		foodlist.add(food);
	}
	
	public void addTeam(Teams team){
		teamsList.add(team);
		setActiveTeam(team);
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
		if(isFinished()){
			return;
		}
		this.setAlt(getAlt() + 1);
		worm = currentWorm();
		currentWorm().setActionPoints(currentWorm().getMaxActionPoints());
		currentWorm().setHitPoints(currentWorm().getHitPoints() + 10);
	}

	public Teams getActiveTeam(){
		return activeTeam;
	}
	
	public void setActiveTeam(Teams team){
		this.activeTeam = team;
	}
	
	public void setActiveProjectile(Projectile proj){
		activeProjectile = proj;
	}
	
	public Projectile getActiveProjectile(){
		return activeProjectile;
	}
	
}
