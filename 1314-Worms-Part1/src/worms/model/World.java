/**
 * A class to create, manage and represent a world in the game.
 * 
 * @invar	The world must have a valid width and height.
 * 		 |	validWidth(getWidth()) && validHeight(getHeight());
 * @invar	Before starting the game, the only objects allowed in the world are food and worms.
 * 		 |	if(!started){
 * 		 |		!getObjects.contains(instanceof Projectile)
 * @invar	Upon finishing, there may only be one worm unless there are more, and they are on the same team.
 * 		 |	if(isFinished() && getWorms() > 1){
 * 		 |		for(Worm worms : getWorms()){worms.getTeam() == getActiveTeam();}
 * @invar	All object within this world must also have this world listed as theirs.
 * 		 |	for(Object obj : getObjects()){obj.getWorld() == this};
 * @invar	The passableMap may not be null.
 * 		 |	getPassable() != null;
 * @invar	Random may not be null.
 * 		 |	getRandom() != null;
 * 
 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

package worms.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import worms.model.superclasses.Object;
import worms.util.Util;

public class World {
	
	/**
	 * All of the variables and constants used in this class.
	 */
	
	private Set<Worm> wormsList;
	private Set<Food> foodlist;
	private Set<Teams> teamsList;
	private Set<Projectile> projList;
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

	
	/**
	 * Constructor to create this world according to the parameters.
	 * 
	 * @param 	width
	 * 			The width of this world.
	 * @param 	height
	 * 			The height of this world.
	 * @param 	passable
	 * 			The boolean containing all the passable areas of this world.
	 * @param 	random
	 * 			The random generator of this world.
	 * @effect	The width, height, passableMap and random generator are set for this world.
	 * 		  |	setWidth(width); setHeight(height); setRandom(random); setPassable(passable);
	 * @throws 	IllegalArgumentException The dimension are valid, random was null, or the Passable Map was null.
	 * 		  | random == null || passable == null || !validWidth(width) || !validHeight(height);
	 * 			
	 */
	
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
		wormsList = new LinkedHashSet<Worm>();
		foodlist = new HashSet<Food>();
		teamsList = new LinkedHashSet<Teams>();
		projList = new HashSet<Projectile>();
	}
	
	/**
	 * Returns whether the provided width is valid.
	 * 
	 * @param 	xWidth
	 * 			The width that is to be checked for validity.
	 * @return	True if the width is a number and beween 0 and MAX_VALUE.
	 * 		  |	(!Double.isNaN(xWidth) && 0 <= xWidth && xWidth <= MAX_VALUE;
	 * 
	 */
	
	public boolean validWidth(double xWidth){
		boolean valid = false;
		if(!Double.isNaN(xWidth)){
			if(0.0 <= xWidth && xWidth <= maxX){
				valid = true;
			}
		}
		return valid;
	}
	
	/**
	 * Returns whether the provided height is valid. 
	 * 
	 * @param 	yHeight
	 * 			The height that is to be checked for validity.
	 * @return	True if the height is a number and beween 0 and MAX_VALUE.
	 * 		  |	(!Double.isNaN(yHeight) && 0 <= yHeight && yHeight <= MAX_VALUE;
	 */
	
	public boolean validHeight(double yHeight){
		boolean valid = false;
		if(!Double.isNaN(yHeight)){
			if(0.0 <= yHeight && yHeight <= maxY){
				valid = true;
			}
		}
		return valid;
	}
	
	/**
	 * Returns the width of this world.
	 * 
	 * @return The width.
	 */
	
	public double getWidth(){
		return width;
	}
	
	/**
	 * Setter for the width of this world
	 * 
	 * @param 	width
	 * 			The width we intend on setting.
	 * @post 	The current width will equal the parameter width.
	 * 		  |	getWidth() == width:
	 * @throws IllegalArgumentException If the width isn't valid.
	 * 		  | !validWidth(width);
	 */
	
	public void setWidth(double width){
		if(!validWidth(width)){
			throw new IllegalArgumentException();
		}
		this.width = width;
	}
	
	/**
	 * Setter for the height of this world
	 * 
	 * @param 	height
	 * 			The height we intend on setting.
	 * @post 	The current height will equal the parameter height.
	 * 		  |	getHeight() == height:
	 * @throws IllegalArgumentException If the height isn't valid.
	 * 		  | !validWidth(height);
	 */
	
	public void setHeight(double height){
		if(!validHeight(height)){
			throw new IllegalArgumentException();
		}
		this.height = height;
	}
	
	/**
	 * Returns the height of this world.
	 * 
	 * @return The height.
	 */
	
	public double getHeight(){
		return height;
	}
	
	/**
	 * Returns the passable Map boolean for this world.
	 * 
	 * @return	The passable map
	 */
	
	public boolean[][] getPassable(){
		return passable;
	}
	
	/**
	 * Returns the random generator for this world.
	 * 
	 * @return	The random generator.
	 */
	
	public Random getRandom(){
		return this.random;
	}
	
	/**
	 * Tells us whether an area is passable for an object or not.
	 * 
	 * @param 	x
	 * 			The x-coordinate where we are going to check.
	 * @param 	y
	 * 			The y-coordinate where we are going to check.
	 * @param 	radius
	 * 			The radius of the object that wants to pass.
	 * @return	True if the area provided by the parameters can't be passed through.
	 */
	
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
	
	/**
	 * Checks wheter the given coordinates are within a circle with given radius.
	 * 
	 * @param 	altX
	 * 			The x-coordinate.
	 * @param 	altY
	 * 			The y-coordinate.
	 * @param 	altRad
	 * 			The radius for the circle.
	 * @return	True if the coordinates are within the circle.
	 */
	
	private boolean reachable(int altX, int altY, int altRad){
		return Util.fuzzyLessThanOrEqualTo(Math.abs(altX), Math.abs(altY), altRad);
	}
	
	/**
	 * Check whether an object is still in the world or not.
	 * 
	 * @param 	x
	 * 			X-coordinate of this object.
	 * @param 	y
	 * 			y-coordinate of this object.
	 * @param 	radius
	 * 			Radius of this object.
	 * @return	True if the object is outside of the game-world.
	 */
	
	public boolean objectInWorld(double x, double y, double radius){
		double pos1 = x + Math.abs(radius);
		double pos2 = x - Math.abs(radius);
		double pos3 = y + Math.abs(radius);
		double pos4 = y - Math.abs(radius);
		if(pos2 < 0.0 || pos3 > getHeight() || pos1 > getWidth() || pos4 < 0){
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if an area is next to an impassable area of the world.
	 * 
	 * @param 	x
	 * 			The x-coordinate.
	 * @param 	y
	 * 			The y-coordinate.
	 * @param 	radius
	 * 			The radius
	 * @return	True if the area is next to, within 0.1 times the radius, of an impassable area and therefor passable.
	 * 		  |	(!isImpassable(x, y, radius) && isImpassable(x, y, radius*1.1));
	 */
	
	public boolean isAdjacent(double x, double y, double radius){
		double newRadius = radius*1.1;
		return(!isImpassable(x, y, radius) && isImpassable(x, y, newRadius));
	}
	 
	/**
	 * Adds a given object to this world;
	 * 
	 * @param 	obj
	 * 			The object to add.
	 * @effect	if the object is a Worm then the method to add a worm will be called.
	 * 		  |	if(obj instanceof Worm) addWorm(obj);	
	 * @effect	if the object is a Projectile then the method to add a Projectile will be called.
	 * 		  |	if(obj instanceof Projectile) addProjectile(obj);	
	 * @effect	if the object is Food then the method to add food will be called.
	 * 		  |	if(obj instanceof Food) addFood(obj);	
	 */
	
	public void addObjects(Object obj){
		if(obj instanceof Food){
			addFood((Food) obj);
		}
		if(obj instanceof Projectile){
			addProjectile((Projectile) obj);
		}
	}
	
	/**
	 * Removes a given object to this world;
	 * 
	 * @param 	obj
	 * 			The object to remove.
	 * @effect	if the object is a Worm then the method to remove a worm will be called.
	 * 		  |	if(obj instanceof Worm) delWorm(obj);	
	 * @effect	if the object is a Projectile then the method to remove a Projectile will be called.
	 * 		  |	if(obj instanceof Projectile) delProjectile(obj);	
	 * @effect	if the object is Food then the method to remove food will be called.
	 * 		  |	if(obj instanceof Food) delFood(obj);	
	 */
	
	public void delObjects(Object obj){
		if(obj instanceof Food){
			delFood((Food) obj);
		}
		if(obj instanceof Worm){
			delWorm((Worm) obj);
		}
		if(obj instanceof Projectile){
			delProjectile((Projectile) obj);
		}
	}
	
	/**
	 * Checks whether this world contains a certain object.
	 * 
	 * @param	obj
	 * 			Object we're checking for.
	 * @return	True if the object list of this world contains the object.
	 * 		  |	getObjects().contains(obj);
	 */
	
	public boolean contains(Object obj){
		return getObjects().contains(obj);
	}
	
	/**
	 * Method to add a worm to this world.
	 * 
	 * @param 	worm
	 * 			The worm that we are adding
	 * @pre		The game should not have been started yet.
	 * 		  |	!getStarted();
	 * @effect	The worm will be added to the list of worms of this world.
	 * 		  |	getWorms().add(worm);
	 */
	
	public void addWorm(){
		Double[] location = new Double[]{0.0,0.0};
		while(location[0] == 0.0 && location[1] == 0.0){
			double xPos = random.nextFloat()*getWidth();
			double yPos = random.nextFloat()*getHeight();
			if((xPos != 0.0 || yPos != 00) && !isImpassable(xPos, yPos, Worm.getMinimalRadius()) && !(xPos + Worm.getMinimalRadius() > getWidth()) && !(xPos-Worm.getMinimalRadius() < 0) && !(yPos+Worm.getMinimalRadius()>getHeight()) && !(yPos - Worm.getMinimalRadius() < 0)){
				location[0] = xPos;
				location[1] = yPos;
			}
			spawnWorm(location[0], location[1]);
		}
	}
	
	/**
	 * Method to spawn a worm in the world.
	 * 
	 * @param 	x
	 * 			The worms x-coordinate.
	 * @param 	y
	 * 			The worms y-coordinate
	 * @return	The worm.
	 * @effect	The worm will have been added to the wormslist.
	 * 		  |	getAllWorms.contains(this) == true;
	 * @effect	The world for this worm will be set as this world.
	 * 		  |	worm.setWorld(this);
	 */
	
	public Worm spawnWorm(double x, double y){
		Worm worm = new Worm(this, x, y);
		worm.setWorld(this);
		addAWorm(worm);
		return worm;
	}
	
	/**
	 * Method to add a worm to the list of worms in this world.
	 * 
	 * @param 	worm
	 * 			The worm to add.
	 */
	
	public void addAWorm(Worm worm){
		if(worm == null){
			throw new IllegalStateException("The worm is null");
		}
		wormsList.add(worm);
	}
	
	/**
	 * Method to remove a worm from this world.
	 * 
	 * @param 	worm
	 * 			The worm that is to be removed.
	 * @pre		The game must be started.
	 * 		  | getStarted();
	 * @effect	The worm will be removed from this world.
	 * 		  |	!getWorms().contains(worm);
	 * @effect	The switchWorm iterator is updated so that it doesn't contain the worm anymore.
	 * 		  |	switchWorm = getWorms().iterator();
	 * @effect 	If the parameter worm was the active worm, the next turn will be started.
	 * 		  |	if(getActiveWorm() == worm){nextTurn();
	 */
	
	public void delWorm(Worm worm){
		if(getWorms().contains(worm)){
			if(getActiveWorm() != worm){
				wormsList.remove(worm);
				switchWorm = wormsList.iterator();
			}
			else if(getActiveWorm() == worm){
				switchWorm.remove();
				nextTurn();
			}
		}
		if(!worm.removed()){
			worm.remove();
		}
		if(teamsList.size() == 1){
			setFinished();
		}
		else if(wormsList.size() <= 1){
			setFinished();
		}
	}
	
	/**
	 * Returns a list of all the objects in this world.
	 * 
	 * @return	All the Worms, Projectiles and Food in this world.
	 */
	
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
	
	/**
	 * Method to add food to this world.
	 * 
	 * @param 	food
	 * 			The food that is going to be added.
	 * @pre		The game should not have been started yet.
	 * 		  |	!getStarted();
	 * @effect	The Food will be added to the list of food of this world.
	 * 		  |	getWorms().add(food);
	 */
	
	public void addFood(Food food){
		if(food.getWorld() == this && isAdjacent(food.getX(), food.getY(), food.getRadius())){
		foodlist.add(food);
		}
	}
	
	/**
	 * Method to spawn food into the world at a random location.
	 * 
	 * @param 	x
	 * 			The food's x-coordinate.
	 * @param 	y
	 * 			The food's y-coordinate
	 * @return	The food.
	 * @post	The food will have been added to the foodlist.
	 * 		  |	getAllFood.contains(this) == true;
	 */
	
	public void spawnFood(){
		Double[] location = new Double[]{0.0,0.0};
		while(location[0] == 0.0 && location[1] == 0.0){
			double xPos = random.nextFloat()*getWidth();
			double yPos = random.nextFloat()*getHeight();
			if((xPos != 0.0 || yPos != 0.0) && !isImpassable(xPos, yPos, Food.getR()) && !(xPos + Food.getR() > getWidth()) && !(xPos-Food.getR() < 0) && !(yPos+Food.getR()>getHeight()) && !(yPos - Food.getR() < 0)){
				location[0] = xPos;
				location[1] = yPos;
			}
			spawnFoodInWorld(location[0], location[1]);
		}
	}
	
	/**
	 * Method to create food and add it to the list of food in this world.
	 * 
	 * @param 	x
	 * 			The x-coordinate for the food to add.
	 * @param 	y
	 * 			The y-coordinate for the food to add.
	 * @effect	A new food object will be created.
	 * 		  | Food food = new Food(this, x, y);
	 * @effect	This food will be added to the list of food in this world.
	 * 		  |	addFood(food);
	 */
	
	
	public Food spawnFoodInWorld(double x, double y){
		Food food = new Food(this, x, y);
		addFood(food);	
		return food;
	}
	
	/**
	 * Method to remove food from this world.
	 * 
	 * @param 	food
	 * 			The food that's going to be removed.
	 * @effect	The food is removed from the world.
	 * 		  |	food.remove();
	 */
	
	public void delFood(Food food){
		if(!food.removed()){
			food.remove();
		}
		foodlist.remove(food);
	}
	
	/**
	 * Method to add a Projectile to this world.
	 * 
	 * @param 	proj
	 * 			Projectile to be added to the world.
	 * @effect	The projectile will be added to the list of projectiles in this world.
	 * 		  |	getProjectiles().add(proj);
	 */
	
	public void addProjectile(Projectile proj){
		if(proj.getWorld() == this){
		projList.add(proj);
		}
	}
	
	/**
	 * Method to remove a projectile from this world.
	 * 
	 * @param 	proj
	 * 			Projectile that is to be removed.
	 * @effect	The projectile is removed from the world.
	 * 	  	  |	getActiveProjectile().remove();
	 */
	
	public void delProjectile(Projectile proj){
		if(!proj.removed()){
			proj.remove();
		}
		projList.remove(proj);
	}
	
	/**
	 * Check whether this team is a valid team.
	 * 
	 * @param 	team
	 * @return	true if the team is not null.
	 * 		  |	getActiveTeam() != null;
	 */
	
	public boolean validTeam(Teams team){
		return (team != null);
	}
	
	/**
	 * Methode to add a team to this world.
	 * 
	 * @param 	name
	 * 			The name of the team to be added.
	 * @effect	A new Team is created
	 * 		  |	Teams team = new Teams(name);
	 * @effect	The team will be added to the teamslist.
	 * 		  |	getTeams().add(team);
	 * @effect	The active team will be set to this team.
	 * 		  | setActiveTeam(team);
	 */
	
	public void addTeam(String name){
		Teams newTeam = new Teams(name);
		teamsList.add(newTeam);
		setActiveTeam(newTeam);
	}
	
	/**
	 * Returns the active team.
	 * 
	 * @return The active team.
	 */
	
	public Teams getActiveTeam(){
		return this.team;
	}
	
	/**
	 * Method to set the active team.
	 * 
	 * @param 	team
	 * 			The team that's going to be the active team.
	 * @effect	The active team will equal this team.
	 * 		  |	getActiveTeam() == team;
	 */
	
	public void setActiveTeam(Teams team){
		this.team = team;
	}
	
	/**
	 * Method to set the active worm.
	 * 
	 * @param 	worm
	 * 			The worm that's going to be the active worm.
	 * @effect	The active worm will equal this worm.
	 * 		  |	getActiveWorm() == worm;
	 * 			
	 */
	
	public void setActiveWorm(Worm worm){
		this.activeWorm = worm;
	}
	
	/**
	 * Returns the active worm.
	 * 
	 * @return The active worm.
	 */
	
	public Worm getActiveWorm(){
		return activeWorm;
	}
	
	/**
	 * Returns a list of all the projectiles.
	 * 
	 * @return Set of projectiles.
	 */
	
	public Set<Projectile> getProjectile(){
		return projList;
	}
	
	/**
	 * Returns a list of all the food.
	 * 
	 * @return Set of food.
	 */
	
	public Set<Food> getFood(){
		return foodlist;
	}
	
	/**
	 * Returns a list of all the worms.
	 * 
	 * @return Set of worms.
	 */
	
	public Set<Worm> getWorms(){
		return wormsList;
	}
	
	/**
	 * Boolean to check whether the game is finished.
	 * 
	 * @return	True if the game is finished.
	 */
	
	public boolean isFinished(){
		return finished ;
	}
	
	/**
	 * Start the worlds next turn.
	 * 
	 * @effect	The next worm in the worms iterator will be selected.
	 * 		  |	setActiveWorm(switchWorm.next())
	 * @effect	The iterator is restarted if the last worm is reached.
	 * 		  |	switchWorm = getWorms().iterator();
	 * @effect	The actionPoints will be reset and the worm will receive 10 Hitpoints.
	 * 		  |	getActiveWorm().setActionPoints(getActiveWorm().getMaxActionPoints());
	 * 		  |	getActiveWorm().setHitPoints(getActiveWorm().getHitPoints() + 10);
	 */
	
	public void nextTurn(){
		if(!switchWorm.hasNext()){
			switchWorm = wormsList.iterator();
		}
		if(isFinished()){
			return;
		}
		setActiveWorm(switchWorm.next());
		getActiveWorm().setActionPoints(getActiveWorm().getMaxActionPoints());
		getActiveWorm().setHitPoints(getActiveWorm().getHitPoints() + 10);
	}

	/**
	 * Returns a list of all teams.
	 * 
	 * @return	List of teams.
	 */
	
	public List<Teams> getAllTeams(){
		List<Teams> list = new ArrayList<Teams>();
		for(Teams teams : teamsList){
			list.add(teams);
		}
		return list;
	}
	
	/**
	 * Method to set the active projectile.
	 * 
	 * @param 	proj
	 * 			The projectile that we want to be the active projectile.
	 * @effect	The active projectile will equal this projectile.
	 * 		  |	getActiveProjectile() == proj
	 */
	
	public void setActiveProjectile(Projectile proj){
		activeProjectile = proj;
	}
	
	/**
	 * Getter for the active projectile.
	 * 
	 * @return	the active projectile.
	 */
	
	public Projectile getActiveProjectile(){
		return activeProjectile;
	}
	
	/**
	 * Method to start the game.
	 * 
	 * @effect	The active team will be set to null.
	 * 		  |	setActiveTeam(null);
	 * @effect	The boolean started will be set to true/
	 * 		  |	setStarted();
	 */
	
	public void start(){
		switchWorm = wormsList.iterator();
		if(switchWorm.hasNext()){
			setActiveWorm(switchWorm.next());
		}
		setActiveTeam(null);
		setStarted();
	}
	
	/**
	 * Method to check the state of the game and start it.
	 * 
	 * @effect 	Boolean started will be set to true.
	 * @effect	If there is only one team left, the game will be finished.
	 * 		  |	if(getTeams().size == 1){setFinished)};
	 * @effect	if there is 1 worm or less left, the game will de finished.
	 * 		  |	if(getWorms().size()<=1){setFinished()};
	 */
	
	public void setStarted(){
		started = true;
		if(getWorms().size() <= 1){
			setFinished();
		}
		if(teamsList.size() == 1){
			setFinished();
		}
	}
	
	/**
	 *	Method to finish the game.
	 *
	 * @effect	The active worm will be set to null.
	 * 		  |	setActiveWorm(null);
	 * @effect	The active team will be set to null.
	 * 		  |	setActiveTeam(null);
	 * @effect	The boolean finished will be set to true. 
	 */
	
	public void setFinished(){
		setActiveWorm(null);
		setActiveTeam(null);
		finished = true;
	}
	
	/**
	 * Returns the winner of the game
	 * 
	 * @return	If teams were used, the team will be returned. Ohterwise the last worm.
	 */
	
	public String getWinner(){
		if(teamsList.size() == 1){
			java.lang.Object[] winners = getAllTeams().toArray();
			Teams winner = (Teams)winners[0];
			String string = "Team " + winner.getTName();
			return string;
		}
		else{
			java.lang.Object[] winnersWorm = getWorms().toArray();
			Worm winnerWorm = (Worm)winnersWorm[0];
			return winnerWorm.getName();
		}
	}
	
	/**
	 * Method to remove a team.
	 * 
	 * @param 	team
	 * 			The team to be removed.
	 * @effect	The team will no longer part of this world.
	 * 		  |	!getTeams().contains(team);
	 */
	
	public void removeTeam(Teams team){
		teamsList.remove(team);
	}
	
	/**
	 * Return the width of a pixel.
	 * 
	 * @return	pixel-width
	 * 		  | getPassable()[0].length;
	 */
	
	public int PXLWidth(){
		return passable[0].length;
	}
	
	/**
	 * Return the height of a pixel.
	 * 
	 * @return	pixel-heigth
	 * 		  | getPassable().length;
	 */
	
	public int PXLHeight(){
		return passable.length;
	}
	
	/**
	 * Return the height scale of a pixel.
	 * 
	 * @return	scale-height
	 * 		  | getHeight()/PXLHeight();
	 */
	
	public double heightPXL(){
		return getHeight()/PXLHeight();
	}
	
	/**
	 * Return the width scale of a pixel.
	 * 
	 * @return	scale-width
	 * 		  | getHeight()/PXLWidth();
	 */
	
	public double widthPXL(){
		return getWidth()/PXLWidth();
	}
}
