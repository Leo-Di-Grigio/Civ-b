package game;

import java.io.IOException;
import java.util.HashMap;

import network.ClientPool;
import network.Message;
import network.Message.Prefix;
import misc.Const;
import misc.Enums;
import misc.Log;

public class Game {
	
	// id
	private static int ID;
	public int id;
	
	// gamedata
	public String name = "";
	public Enums.GameState state;
	
	// playres
	public HashMap<Integer, Player> players;
	
	public Game(String name){
		this.id = ID++;
		this.name = name;
		this.players = new HashMap<Integer, Player>();
		state = Enums.GameState.PREPEARING;
	}
	
	public void addPlayer(int clientId) throws IOException{
		if(players.size() < Const.playersMax){
			if(players.containsKey(clientId)){
				Log.err("Player already in game");
				ClientPool.sendMsg(clientId, new Message(Prefix.GAME_CONNECTION_ERR, "already played"));
			}
			else{
				players.put(clientId, new Player());
				ClientPool.sendMsg(clientId, new Message(Prefix.GAME_CONNECTION_SUCESS, ""));
			}
		}
		else{
			ClientPool.sendMsg(clientId, new Message(Prefix.GAME_CONNECTION_ERR, "server full"));
		}
	}
}
