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
	
	public void addTeam(String newName) throws IllegalNameException{
		setTName(newName);
		teamlist.add(name);
	}
	
	public void setTName(String newName) throws IllegalNameException{
		if(!Worm.isValidName(newName)){
			throw new IllegalNameException(newName);
		}
		name = newName;
	}
	
	public String getTName(int alt){
		return teamlist.get(alt);
	}
	
	public ArrayList<String> returnTeams(){
		return teamlist;
	}

}
