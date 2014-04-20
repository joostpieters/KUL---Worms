package worms.model;

import java.util.ArrayList;

public class Teams {
	
	private String name;
	private World world;
	public ArrayList<String> teamlist;
	
	public Teams(World world, String name) throws IllegalNameException{
		if(!Worm.isValidName(name)){
			throw new IllegalNameException(name);
		}
		this.name = name;
		this.world = world;
	}
	
	public void setTName(String newName) throws IllegalNameException{
		if(!Worm.isValidName(newName)){
			throw new IllegalNameException(newName);
		}
		this.name = newName;
	}
	
	public String getTName(){
		return name;
	}
	
	public World getTWorld(){
		return world;
	}

	

}
