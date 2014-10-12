package game;

import misc.Enums;

public class Game {
	
	// id
	private static int ID;
	public int id;
	
	// gamedata
	public String name = "";
	public Enums.GameState state;
	
	public Game(String name){
		this.id = ID++;
		this.name = name;
		state = Enums.GameState.PREPEARING;
	}
}
