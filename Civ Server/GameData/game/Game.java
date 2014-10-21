package game;

import java.io.IOException;

import misc.Log;

public class Game {
	
	// id
	private static int ID;
	public int id;
	
	// gamedata
	public String title = "";
	public long gameSeed = 0L;
	
	// Logic
	public GameLogic logic;
	
	public Game(String title, int mapSizeX, int mapSizeY, int playersMax) throws IOException{
		this.id = ID++;
		this.gameSeed = System.currentTimeMillis();
		this.title = title;
		
		// Logic thread
		logic = new GameLogic(id, gameSeed, mapSizeX, mapSizeY, playersMax);
		
		Log.service("Game created. Name: " + this.title + " playersMax: " + playersMax);
	}
}
