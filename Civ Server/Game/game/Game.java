package game;

import gamedb.GameDB;

import java.io.IOException;
import java.sql.SQLException;

import misc.Log;

public class Game {
	
	// id
	private static int ID;
	public int id;
	
	// gamedata
	private GameDB data;
	
	public String title = "";
	public long gameSeed = 0L;
	
	// Logic
	public GameData logic;
	
	public Game(String title, int mapSizeX, int mapSizeY, int playersMax, String dbPath) throws IOException{
		this.id = ID++;
		this.gameSeed = System.currentTimeMillis();
		this.title = title;
		
		// Logic thread
		logic = new GameData(id, gameSeed, mapSizeX, mapSizeY, playersMax);
		data = new GameDB(dbPath);

		if(data.connect()){
			Log.service("Game created. Name: " + this.title + " playersMax: " + playersMax);
		}
		else{
			Log.service("Game creating error. DB \"" + data.dbPath + "\" is not found.");
		}
		
		try {
			this.data.showTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
