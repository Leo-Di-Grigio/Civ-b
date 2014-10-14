package game;

import java.io.IOException;
import java.util.HashMap;

import player.Player;
import builder.GameMapGenerator;
import network.ClientPool;
import net.Message;
import net.Message.Prefix;
import misc.Const;
import misc.Enums;
import misc.Log;

public class Game {
	
	// id
	private static int ID;
	public int id;
	
	// gamedata
	public String name = "";
	public int playersMax = 0;
	public long gameSeed = 0L;
	public Enums.GameState state;
	
	// game map
	public int sizeX = 0;
	public int sizeY = 0;	
	public byte [][] heightMap;
	
	// playres
	public HashMap<Integer, Player> players;

	
	public Game(String name, int mapSizeX, int mapSizeY, int playersMax){
		// id
		this.id = ID++;
		this.gameSeed = System.currentTimeMillis();
		
		// title data
		this.name = name;
		this.playersMax = playersMax;
		this.players = new HashMap<Integer, Player>();
		
		// map
		this.sizeX = mapSizeX;
		this.sizeY = mapSizeY;
		this.heightMap = GameMapGenerator.buildHeightMap(gameSeed, mapSizeX, mapSizeY);
			
		state = Enums.GameState.PREPEARING;
	}
	
	public void addPlayer(int clientId) throws IOException{
		if(players.size() < Const.playersMax){
			if(players.containsKey(clientId)){
				Log.err("Player already in game");
				ClientPool.sendMsg(clientId, new Message(Prefix.GAME_CONNECTION_ERR, "already played"));
			}
			else{
				players.put(clientId, new Player("Test Player ID: " + clientId));
				ClientPool.sendMsg(clientId, new Message(Prefix.GAME_CONNECTION_SUCESS, "" + gameSeed + ":" + sizeX + ":" + sizeY));
			}
		}
		else{
			ClientPool.sendMsg(clientId, new Message(Prefix.GAME_CONNECTION_ERR, "server full"));
		}
	}
}
