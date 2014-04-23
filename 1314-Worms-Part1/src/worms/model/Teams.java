package worms.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Teams {
	
	private String name;
	private World world;
	private Set<Worm> allWorms;
	
	public Teams(String name) throws IllegalArgumentException{
		if(!Worm.isValidName(name)){
			throw new IllegalArgumentException(name);
		}
		this.name = name;
		this.allWorms = new HashSet<Worm>();
	}
	
	public void addWorm(Worm worm){
		allWorms.add(worm);
	}
	
	public void removeWorm(Worm worm){
		if(allWorms.size() != 0){
			allWorms.remove(worm);
		}
		else{
			getWorld().removeTeam(this);
		}
	}
	
	public World getWorld(){
		return this.world;
	}
	
	public void setWorld(World world){
			this.world = world;
	}
	
	public Set<Worm> getAllWorms(){
		return allWorms;
	}

	public String getTName(){
		return this.name;
	}
	
	public void setTName(String newName){
		if(!Worm.isValidName(newName)){
			throw new IllegalArgumentException("This is not a valid name: "+newName);
		}
		this.name = newName;
	}
}
