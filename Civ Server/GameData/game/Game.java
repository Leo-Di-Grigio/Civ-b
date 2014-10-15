package game;

import java.io.IOException;
import builder.GameMapGenerator;
import misc.Enums;
import misc.Log;

public class Game {
	
	// id
	private static int ID;
	public int id;
	
	// gamedata
	public String name = "";
	public int  playersMax = 0;
	public long gameSeed = 0L;
	public Enums.GameState state;
	
	// game map
	public int sizeX = 0;
	public int sizeY = 0;	
	public byte [][] heightMap;
	
	// playres
	public GamePlayers players;
	
	// messaging
	protected GameBroadcasting broad;

	
	public Game(String name, int mapSizeX, int mapSizeY, int playersMax){
		// id
		this.id = ID++;
		this.gameSeed = System.currentTimeMillis();
		
		// title data
		this.name = name;
		this.playersMax = playersMax;
		this.players = new GamePlayers(id);
		
		// map
		this.sizeX = mapSizeX;
		this.sizeY = mapSizeY;
		this.heightMap = GameMapGenerator.buildHeightMap(gameSeed, mapSizeX, mapSizeY);
		
		// messaging
		broad = new GameBroadcasting(players);
		
		// status
		state = Enums.GameState.PREPEARING;
		
		Log.service("Game created. Name: " + this.name + " playersMax: " + this.playersMax);
	}
	
	public void addPlayer(int clientId, String playerName) throws IOException{
		players.add(clientId, playerName, playersMax, gameSeed, sizeX, sizeY, broad);
	}
	
	public void removePlayer(int clientId) throws IOException{
		players.remove(clientId, broad);
	}
	
	public void sendPlayersList(int clientId) throws IOException{
		players.sendPlayesList(clientId);
	}
}
