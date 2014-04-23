package worms.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import worms.model.superclasses.Object;
import worms.util.Util;

/**
 * 
 * @author Kristof Achten <kristof.achten@student.kuleuven.be>
 * @version 1.0
 *
 */

public class World {
	
	private ArrayList<Worm> wormsList;
	private ArrayList<Food> foodlist;
	private ArrayList<Teams> teamsList;
	private ArrayList<Projectile> projList;
	private Iterator<Worm> switchWorm;

	private double width;
	private double height;
	private double maxX = Double.MAX_VALUE;
	private double maxY = Double.MAX_VALUE;
	private boolean[][] passable;
	private Projectile activeProjectile;
	private Teams team;
	private boolean finished = false;
	private Worm activeWorm;
	private Random random;
	private boolean started;

	public World(double width, double height, boolean[][] passable, Random random) throws IllegalArgumentException{
		if((!validWidth(width) || !validHeight(height))){
			throw new IllegalArgumentException("Dimensions are not valid!");
		}
		if(random == null){
			throw new IllegalArgumentException("random was null, which is not a valid random");
		}
		if(passable == null){
			throw new IllegalArgumentException("Passable map is null!");
		}
		this.width = width;
		this.height = height;
		this.passable = passable;
		this.random = random;
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
		if(pos2 < 0.0 || pos3 > getHeight() || pos1 > getWidth() || pos4 < 0){
			return false;
		}
		return true;
	}
	
	public boolean isAdjacent(double x, double y, double radius){
		double newRadius = radius*1.1;
		return(!isImpassable(x, y, radius) && isImpassable(x, y, newRadius));
	}
	 
	public void addObjects(Object obj){
		if(obj instanceof Worm){
			addWorm((Worm) obj);
		}
		if(obj instanceof Food){
			addFood((Food) obj);
		}
		if(obj instanceof Projectile){
			addProjectile((Projectile) obj);
		}
	}
	
	public void addWorm(Worm worm){
		if(worm.getWorld() == this && isAdjacent(worm.getX(), worm.getY(), worm.getRadius())){
			wormsList.add(worm);
		}
	}
	
	public List<Object> getObjects(){
		ArrayList<Object> all = new ArrayList<Object>();
		for(Food food : foodlist){
			all.add(food);
		}
		for(Worm worm : wormsList){
			all.add(worm);
		}
		for(Projectile proj : projList){
			all.add(proj);
		}
		return all;
	}
	
	public void addFood(Food food){
		if(food.getWorld() == this && isAdjacent(food.getX(), food.getY(), food.getRadius())){
		foodlist.add(food);
		}
	}
	
	public void addProjectile(Projectile proj){
		if(proj.getWorld() == this && isAdjacent(proj.getX(), proj.getY(), proj.getRadius())){
		projList.add(proj);
		}
	}
	
	public boolean validTeam(Teams team){
		return (team != null);
	}
	
	public void addTeam(String name){

		Teams newTeam = new Teams(name);
		if(!validTeam(newTeam)){
			throw new IllegalStateException("Not a valid team");
		}
		teamsList.add(newTeam);
		setActiveTeam(newTeam);
	}
	
	public Teams getActiveTeam(){
		return this.team;
	}
	
	public void setActiveTeam(Teams team){
		this.team = team;
	}
	
	public void setActiveWorm(Worm worm){
		this.activeWorm = worm;
	}
	
	public Worm getActiveWorm(){
		return activeWorm;
	}
	
	public ArrayList<Projectile> getProjectile(){
		return projList;
	}
	
	public ArrayList<Food> getFood(){
		return foodlist;
	}
	
	public ArrayList<Worm> getWorms(){
		return wormsList;
	}
	
	public boolean isFinished(){
		return finished ;
	}
	
	public void nextTurn(){
		if(isFinished()){
			return;
		}
		if(!switchWorm.hasNext()){
			switchWorm = wormsList.iterator();
		}
		setActiveWorm(switchWorm.next());
		getActiveWorm().setActionPoints(getActiveWorm().getMaxActionPoints());
		getActiveWorm().setHitPoints(getActiveWorm().getHitPoints() + 10);
	}

	public List<Teams> getAllTeams(){
		List<Teams> list = new ArrayList<Teams>();
		for(Teams teams : teamsList){
			list.add(teams);
		}
		return list;
	}
	
	public void setActiveProjectile(Projectile proj){
		activeProjectile = proj;
	}
	
	public Projectile getActiveProjectile(){
		return activeProjectile;
	}
	
	public void start(){
		switchWorm = wormsList.iterator();
		if(switchWorm.hasNext()){
			setActiveWorm(switchWorm.next());
		}
		setStarted();
		setActiveTeam(null);
	}
	
	public void setStarted(){
		started = true;
		if(getWorms().size() == 1){
			setFinished();
		}
		if(teamsList.size() == 1){
			setFinished();
		}
	}
	
	public void setFinished(){
		setActiveWorm(null);
		setActiveTeam(null);
		finished = true;
	}
	
	public String getWinner(){
		if(teamsList.size() != 0){
			return teamsList.get(0).getTName();
		}
		else{
			String winners = wormsList.get(0).getName();
			winners += ".";
			return winners;
		}
	}
	
	public boolean suitablePos(Object obj) {
		boolean found = false;
		double curX = obj.getX();
		double curY = obj.getY();
		
		for (int i=0; i<10; i++) {

			switch (random.nextInt(4)) {
			case 0:
				curX = 0.0+obj.getRadius()*1.1;
				curY = random.nextDouble()*(getHeight()-2*obj.getRadius()*1.1) + obj.getRadius()*1.1;
				break;
			case 1:
				curX = getWidth()-obj.getRadius()*1.1;
				curY = random.nextDouble()*(getHeight()-2*obj.getRadius()*1.1) + obj.getRadius()*1.1;
				break;
			case 2:
				curX = random.nextDouble()*(getWidth()-2*obj.getRadius()*1.1) + obj.getRadius()*1.1;
				curY = 0.0+obj.getRadius()*1.1;
				break;
			default:
				curX = random.nextDouble()*(getWidth()-2*obj.getRadius()*1.1) + obj.getRadius()*1.1;
				curY = getHeight()-obj.getRadius()*1.1;
			}
			if (isAdjacent(curX,curY,obj.getRadius())){
				obj.setX(curX);
				obj.setY(curY);
				found = true;
				}
			double slope = slope(curX, curY, getWidth()/2, getHeight()/2);
			while (!found && !objectInWorld(curX,curY,obj.getRadius()))
				{
				curX = curX-0.05*Math.cos(slope)*obj.getRadius();
				curY = curY-0.05*Math.sin(slope)*obj.getRadius();
				if (isAdjacent(curX,curY,obj.getRadius())){
					obj.setX(curX);
					obj.setY(curY);
					found = true;
					}
				}		
		}
		return found;
	}
	
	public double slope(double x, double y, double x1, double y1){
		double s = Math.atan((y-y1)/(x-x1));
		if((x-x1) >= 0.0){
			return s;
		}
		else{
			return s + Math.PI;
		}
	}
}
