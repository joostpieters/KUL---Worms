package worms.model;

import java.util.Collection;
import java.util.Random;

/**
 * Class used to implement the IFacade-class.
 * 
 * @version   1.0
 * @author 	  Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: 	  https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 * 
 */

public class Facade implements IFacade {
	
	private Random r = new Random();

	
	@Override
	public Worm createWorm(World world, double x, double y, double direction, double radius, String name) throws ModelException{

			return new Worm(world, x, y, direction, radius, name);
	}

	/**@Override
	public boolean canMove(Worm worm, int nbSteps) {
		return worm.canMove(nbSteps);
	}

	@Override
	public void move(Worm worm, int nbSteps) throws IllegalArgumentException{
		try{
			worm.move(nbSteps);
		}
		catch(IllegalArgumentException e){
			throw new ModelException("Invalid move");
		}
	}*/

	@Override
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	}

/*	@Override
	public void jump(Worm worm) throws ModelException{
			try{
				worm.jump();
			}
			catch(Exception e){
				throw new ModelException("Unable to jump");
			}
	}

	@Override
	public double getJumpTime(Worm worm) {
		return worm.getJumpTime();
	}*/

	@Override
	public double[] getJumpStep(Worm worm, double t) throws ModelException {
		try{
			return worm.getJumpStep(t);
		}
		catch(Exception e){
			throw new ModelException("Unable to perform a jump");
		}
		
	}

	@Override
	public double getX(Worm worm) {
		return worm.getX();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getY();
	}

	@Override
	public double getOrientation(Worm worm) {
		return worm.getOrientation();
	}

	@Override
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	@Override
	public void setRadius(Worm worm, double newRadius) {
		worm.setRadius(newRadius);
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		return Worm.getMinimalRadius();
	}

	@Override
	public int getActionPoints(Worm worm) {
		return worm.getActionPoints();
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return worm.getMaxActionPoints();
	}

	@Override
	public String getName(Worm worm) {
		return worm.getName();
	}

	@Override
	public void rename(Worm worm, String newName){
			try{
				worm.rename(newName);
			}
			catch(IllegalArgumentException e){
				throw new ModelException(e.getMessage());
			}
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

	@Override
	public void addEmptyTeam(World world, String newName){
		try{
			world.addTeam(new Teams(world, newName));
		}
		catch(IllegalArgumentException name){
			throw new ModelException("Invalid name for the team");
		}
	}

	@Override
	public void addNewFood(World world) {
			boolean placed = false;
			while(!placed){
				try{
					Food food = new Food(world, world.getWidth()/r.nextInt((int)world.getWidth()), world.getHeight()/r.nextInt((int)world.getHeight()));
					world.addFood(food);
					placed = true;
				}
				catch(Exception e){
					placed = false;
				}
			}
	}

	@Override
	public void addNewWorm(World world) {
		boolean placed = false;
		while(!placed){
			try{
				Worm worm = new Worm(world, world.getWidth()/r.nextInt((int)world.getWidth()), world.getHeight()/r.nextInt((int)world.getHeight()), Math.PI/4, 0.30, "Name");
				world.addWorm(worm);
				placed = true;
			}
			catch(Exception e){
				placed = false;
			}
		}
	}

	@Override
	public boolean canFall(Worm worm) {
		return worm.canFall();
	}

	@Override
	public boolean canMove(Worm worm) {
		return worm.canMove();
	}

	@Override
	public Food createFood(World world, double x, double y) {
		return new Food(world, x, y);
	}

	@Override
	public World createWorld(double width, double height,
			boolean[][] passableMap, Random random) {

		return new World(width, height, passableMap, random);
	}


	@Override
	public void fall(Worm worm) {
		worm.fall();
	}

	@Override
	public Projectile getActiveProjectile(World world) {
		return world.getProjectile().get(0);
	}

	@Override
	public Worm getCurrentWorm(World world) {
		return world.currentWorm();
	}

	@Override
	public Collection<Food> getFood(World world) {
		return world.getFood();
	}

	@Override
	public int getHitPoints(Worm worm) {
		return worm.getHitPoints();
	}

	@Override
	public double[] getJumpStep(Projectile projectile, double t) {
		return projectile.JumpStep(t);
	}

	@Override
	public double getJumpTime(Projectile projectile, double timeStep) {
		return projectile.jumpTime(timeStep);
	}

	@Override
	public double getJumpTime(Worm worm, double timeStep) {
		return worm.getJumpTime(timeStep);
	}

	@Override
	public int getMaxHitPoints(Worm worm) {
		return worm.getMaxHitPoints();
	}

	@Override
	public double getRadius(Food food) {
		return food.getRadius();
	}

	@Override
	public double getRadius(Projectile projectile) {
		return projectile.getRadius();
	}

	@Override
	public String getSelectedWeapon(Worm worm) {
		return worm.getWeapon();
	}

	@Override
	public String getTeamName(Worm worm) {
		//Teams team = worm.getTeam();
		//return team.getTName();
		return "geoff";
	}

	@Override
	public String getWinner(World world) {
		return world.getWinner();
	}

	@Override
	public Collection<Worm> getWorms(World world) {
		return world.getWorms();
	}

	@Override
	public double getX(Food food) {
		return food.getX();
	}

	@Override
	public double getX(Projectile projectile) {
		return projectile.getX();
	}

	@Override
	public double getY(Food food) {
		return food.getY();
	}

	@Override
	public double getY(Projectile projectile) {
		return projectile.getY();
	}

	@Override
	public boolean isActive(Food food) {
		return (!food.isRemoved());
	}

	@Override
	public boolean isActive(Projectile projectile) {
		return (!projectile.isRemoved());
	}

	@Override
	public boolean isAdjacent(World world, double x, double y, double radius) {
		return world.isAdjacent(x, y, radius);
	}

	@Override
	public boolean isAlive(Worm worm) {
		return (!worm.isRemoved());
	}

	@Override
	public boolean isGameFinished(World world) {
		return world.isFinished();
	}

	@Override
	public boolean isImpassable(World world, double x, double y, double radius) {
		return world.isImpassable(x, y, radius);
	}

	@Override
	public void jump(Projectile projectile, double timeStep) {
		projectile.shoot();
	}

	@Override
	public void jump(Worm worm, double timeStep) {
		try{
			worm.jump(timeStep);
		}
		catch(Exception e){
			throw new ModelException("Can't jump");
		}
	}

	@Override
	public void move(Worm worm) {
		try{
			worm.move();
			}
		catch(Exception e){
			throw new ModelException("Not able to move there.");
		}
	}

	@Override
	public void selectNextWeapon(Worm worm) {
		worm.selectNextWeapon();
	}

	@Override
	public void shoot(Worm worm, int yield) {
		worm.shoot(yield);
	}

	@Override
	public void startGame(World world) {
		world = new World(world.getHeight(), world.getWidth(), world.getPassable(), r);
	}

	@Override
	public void startNextTurn(World world) {
		world.nextTurn();
	}

}
