package game;

import gamedata.GameData;
import gamedb.GameDB;
import java.io.IOException;
import misc.Log;

public class Game {
	
	// id
	private static int ID;
	public int id;
		
	public String title = "";
	public long gameSeed = 0L;
	
	// gamedata
	public GameData gamedata;
	private GameDB db;
	
	public Game(String title, int mapSizeX, int mapSizeY, int playersMax, int tMin, int tMax, String dbPath) throws IOException{
		this.id = ID++;
		this.gameSeed = System.currentTimeMillis();
		this.title = title;
		
		// GameData
		db = new GameDB(dbPath);
		
		if(db.connect()){
			Log.service("Game created. Name: " + this.title + " playersMax: " + playersMax);
			gamedata = new GameData(id, gameSeed, mapSizeX, mapSizeY, playersMax, tMin, tMax, db);
		}
		else{
			Log.service("Game creating error. DB \"" + db.dbFilePath + "\" is not found.");
		}
	}
}
