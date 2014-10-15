package game;

import java.io.IOException;
import java.util.HashMap;

import player.Player;
import builder.GameMapGenerator;
import network.ClientPool;
import net.Message;
import net.Message.Prefix;
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
	
	// messaging
	protected GameBroadcasting broad;

	
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
		
		// messaging
		broad = new GameBroadcasting(this.players);
		
		// status
		state = Enums.GameState.PREPEARING;
		
		Log.service("Game created. Name: " + this.name + " playersMax: " + this.playersMax);
	}
	
	public void addPlayer(int clientId, String playerName) throws IOException{
		if(players.size() < playersMax){
			if(players.containsKey(clientId)){
				Log.err("Player already in game");
				ClientPool.sendMsg(clientId, new Message(Prefix.GAME_JOIN_ERR, "already played"));
			}
			else{
				Player player = new Player(playerName);
				players.put(clientId, player);
				ClientPool.getClient(clientId).setGameId(this.id);
				ClientPool.sendMsg(clientId, new Message(Prefix.DATA_GAME, "" + gameSeed + ":" + sizeX + ":" + sizeY));
				broad.send(player.toMessage());
			}
		}
		else{
			ClientPool.sendMsg(clientId, new Message(Prefix.GAME_JOIN_ERR, "server full"));
		}
	}
	
	public void removePlayer(int clientId){
		if(players.containsKey(clientId)){
			players.remove(clientId);
			ClientPool.getClient(clientId).gameId = -1;
			Log.service("Player ID: " + clientId + " leave the game ID: " + this.id);
		}
	}
}
