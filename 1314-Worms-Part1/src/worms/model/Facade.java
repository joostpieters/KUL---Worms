package worms.model;

import java.util.Collection;
import java.util.Random;

import worms.gui.game.IActionHandler;
import worms.model.expressions.Expression;
import worms.model.programs.ParseOutcome;
import worms.model.programs.ProgramParser;
import worms.model.statements.Statement;
import worms.model.types.Type;

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
	
	public Facade(){
	}
	
	@Override
	public Worm createWorm(World world, double x, double y, double direction, double radius, String name, Program program){
			Worm worm = new Worm(world, x, y, direction, radius, name, program);
			return worm;
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) {
		if(!worm.canTurn(angle)){
			throw new ModelException("Not enough actionpoints left to perform this turn.");
		}
		worm.turn(angle);
	}

	@Override
	public double[] getJumpStep(Worm worm, double t){
		if(!worm.canJump()){
			throw new ModelException("Worm cannot jump: no AP left");
		}
		return worm.jumpStep(t);
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
		try{
			worm.setRadius(newRadius);
		}
		catch(Exception e){
			throw new ModelException("Something wrong with this radius: "+e.getMessage());
		}
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
			catch(Exception e){
				throw new ModelException("Something wrong with this name: "+e.getMessage());
			}
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

	@Override
	public void addEmptyTeam(World world, String newName){
			try{
				world.addTeam(newName);
			}
			catch(Exception e){
				throw new ModelException("Something went wront while creating a new team: "+e.getMessage());
			}

	}

	@Override
	public void addNewFood(World world) {
				world.spawnFood();
	}

	@Override
	public boolean canFall(Worm worm) {
		if(worm.isActive()){
			return !(worm.getWorld().isAdjacent(worm.getX(), worm.getY(), worm.getRadius()));
		}
		else{
			return false;
		}
	}

	@Override
	public boolean canMove(Worm worm) {
		return worm.canMove();
	}

	@Override
	public Food createFood(World world, double x, double y) {
		Food food = new Food(world, x, y);
		food.setWorld(world);
		world.addFood(food);
		return food;
	}

	@Override
	public World createWorld(double width, double height, boolean[][] passableMap, Random random) {
		try{
			return new World(width, height, passableMap, random);
		}
		catch(Exception e){
			throw new ModelException("Could not create this world: "+e.getMessage());
		}
	}

	@Override
	public void fall(Worm worm) {
		worm.fall();
	}

	@Override
	public Projectile getActiveProjectile(World world) {
		return world.getActiveProjectile();
	}

	@Override
	public Worm getCurrentWorm(World world) {
		return world.getActiveWorm();
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
		if(t < 0){
			throw new ModelException("Invalid time to calculate the JumpStep with.");
		}
		else{
			return projectile.jumpStep(t);
		}
	}

	@Override
	public double getJumpTime(Projectile projectile, double timeStep) {  //setWorld not coming through for some reason :/
		return projectile.jumpTime(timeStep);
	}

	@Override
	public double getJumpTime(Worm worm, double timeStep) {
		try{
			return worm.jumpTime(timeStep);
		}
		catch(Exception e){
			System.out.println("Worm no longer exists");
			worm.remove();
			return 0;
		}
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
		if(worm.getTeam() != null){
			return worm.getTeam().getTName();
		}
		else return null;
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
		return food.isActive();
	}

	@Override
	public boolean isActive(Projectile projectile) {
		return (!projectile.isActive());
	}

	@Override
	public boolean isAdjacent(World world, double x, double y, double radius) {
		return world.isAdjacent(x, y, radius);
	}

	@Override
	public boolean isAlive(Worm worm) {
		return (worm.isActive());
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
		try{
			projectile.jump(timeStep);
		}
		catch(Exception e){
			throw new ModelException("Not able to shoot with following error: "+e.getMessage());
		}
	}

	@Override
	public void jump(Worm worm, double timeStep) {
			try{
				worm.jump(timeStep);
			}
			catch(Exception e){
				throw new ModelException("Worm can't jump: "+e.getMessage());
			}
	}

	@Override
	public void move(Worm worm) {

	try{
			worm.move();
		}
		catch(Exception e){
			throw new ModelException("Not able to move: "+e.getMessage());
		}
	}

	@Override
	public void selectNextWeapon(Worm worm) {
		worm.selectNextWeapon();
	}

	@Override
	public void shoot(Worm worm, int yield) {
			try{
				worm.shoot(yield);
			}
			catch(Exception e){
				throw new ModelException("Not able to fire");
			}

	}

	@Override
	public void startGame(World world) {
		world.start();
	}

	@Override
	public void startNextTurn(World world) {
		world.nextTurn();
	}

	@Override
	public void addNewWorm(World world, Program program) {
		world.addWorm(program);
	}

	@Override
	public ParseOutcome<?> parseProgram(String programText, IActionHandler handler) {
		ProgramParser<Expression, Statement, Type> parser = new ProgramParser<Expression, Statement, Type>(new ProgramFactoryImp());
		parser.parse(programText);
		if(!parser.getErrors().isEmpty()){
			return ParseOutcome.failure(parser.getErrors());
		}
		else{
			Program program = new Program(parser.getStatement(), handler, parser.getGlobals());
			return ParseOutcome.success(program);
		}
	}

	@Override
	public boolean hasProgram(Worm worm) {
		return worm.hasActiveProgram();
	}

	@Override
	public boolean isWellFormed(Program program) {
		return true; //Not for students that work alone.
	}

}
