package worms.model;
import java.util.ArrayList;
import java.util.List;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of Teams containing worms.
 * 
 * @invar	World must always exist.
 * 		 |	getWorld() != null
 * @invar	The name of a Team must be a valid name.
 * 		 |	Worm.isValidName(getTName());
 * 
 * @version 1.0
 * @author 	Kristof Achten <kristof.achten@student.kuleuven.be>
 * GitHub: https://github.com/Divyak156/OGPProject.git
 * StudentNr: r0462748 - 1ste Bachelor informatica
 *
 */

public class Teams {
	
	/**
	 * All of the variables and constants used in this class.
	 */
	
	private String name;
	private World world;
	private List<Worm> allWorms;
	
	/**
	 * Constructor for the teams.
	 * 
	 * @param 	name
	 * 			The name we want the team to have.
	 * @throws 	IllegalArgumentException The name of this Team contains characters other than all numbers, quotationmarks, spaces or letters.
	 * 		  |	!Worm.isValidName(name);
	 * @effect	The team's name is set to the parameter name.
	 * 		  | setTName(name);
	 * @effect	A new list is created that will later on contain the members of this team.
	 * 		  |	allWorms = new HashSet<Worm>();
	 * 
	 */
	
	@Raw
	public Teams(String name) throws IllegalArgumentException{
		if(!Worm.isValidName(name)){
			throw new IllegalArgumentException(name);
		}
		this.name = name;
		this.allWorms = new ArrayList<Worm>();
	}
	
	/**
	 * Method to add a worm to this team.
	 * 
	 * @param 	worm
	 * 			The worm we want to add.
	 * @post	The given worm will be a part of this team.
	 * 		  |	getAllWorms().contains(worm) == true;
	 */
	
	
	public void addWorm(Worm worm){
		allWorms.add(worm);
	}
	
	/**
	 * Method to remove a worm from this team.
	 * 
	 * @param 	worm
	 * 			The worm we want to remove.
	 * @post	The given worm will no longer be part of this team.
	 * 		  | getAllWorms().contains(worm) == false;
	 * @effect	If the team is empty, it will be removed.
	 * 		  | getWorld().removeTeam(this);	
	 */
	
	public void removeWorm(Worm worm){
		if(allWorms.size() != 0){
			allWorms.remove(worm);
		}
		else{
			getWorld().removeTeam(this);
		}
	}
	
	/**
	 * Get the world this Team belongs to.
	 * 
	 * @return 	world
	 * 			The current world.
	 * 
	 */
	
	@Basic 
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Method to set the world this team belongs to.
	 * 
	 * @param 	world
	 * 			The world we want this team to be in.
	 * @post	The world will equal this world.
	 * 		  |	getWorld() == world;
	 */
	
	@Basic @Raw
	public void setWorld(World world){
			this.world = world;
	}
	
	/**
	 * Return a list of all members of this team.
	 * 
	 * @return	allWorms
	 * 			The list containing all the worms that are a part of this team.
	 */
	
	@Basic
	public List<Worm> getAllWorms(){
		return allWorms;
	}

	/**
	 * Return the name of this team.
	 *
	 * @return	name
	 * 			The name of this team.
	 */

	@Basic
	public String getTName(){
		return this.name;
	}
	
	/**
	 * Method to set the name of this team.
	 * 
	 * @param 	newName
	 * 			The new name.
	 * @post	The current name will equal the parameter name.
	 * 		  |	getTName().equals(name);
	 * @throws	IllegalArgumentException This name contains symbols other than spaces, quotationmarks, letters and numbers.
	 * 		  |	!Worm.isValidName(newName);
	 */
	
	public void setTName(String newName){
		if(!Worm.isValidName(newName)){
			throw new IllegalArgumentException("This is not a valid name: "+newName);
		}
		this.name = newName;
	}
}
