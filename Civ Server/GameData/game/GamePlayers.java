package game;

import java.io.IOException;
import java.util.HashMap;

import misc.Log;
import net.Message;
import net.Message.Prefix;
import network.ClientPool;
import player.Player;

public class GamePlayers {
	
	protected int gameId;
	protected HashMap<Integer, Player> players;
	
	public GamePlayers(int gameId){
		this.gameId = gameId;
		this.players = new HashMap<Integer, Player>();
	}
	
	public int size(){
		return players.size();
	}
	
	public void add(int clientId, String playerName, int  playersMax, long gameSeed, int sizeX, int sizeY, GameBroadcasting broad) throws IOException{
		if(players.size() < playersMax){
			if(players.containsKey(clientId)){
				Log.err("Player already in game");
				ClientPool.sendMsg(clientId, new Message(Prefix.GAME_JOIN_ERR, "already played"));
			}
			else{
				Player player = new Player(clientId, playerName);
				players.put(clientId, player);
				ClientPool.getClient(clientId).setGameId(gameId);
				
				// broad data
				broad.send(player.toMessage());
				
				// send data
				ClientPool.sendMsg(clientId, new Message(Prefix.DATA_GAME, "" + gameSeed + ":" + sizeX + ":" + sizeY));
			}
		}
		else{
			ClientPool.sendMsg(clientId, new Message(Prefix.GAME_JOIN_ERR, "server full"));
		}
	}
	
	public void remove(int clientId, GameBroadcasting broad) throws IOException{
		if(players.containsKey(clientId)){
			players.remove(clientId);
			ClientPool.getClient(clientId).gameId = -1;
			Log.service("Player ID: " + clientId + " leave the game ID: " + gameId);
			broad.send(new Message(Prefix.DEL_PLAYER, "" + clientId));
		}
	}
	
	public void sendPlayesList(int clientId) throws IOException{
		for(Player item: players.values()){
			ClientPool.sendMsg(clientId, item.toMessage());
		}
	}
	
	public void readyCheckPlayer(int clientId, GameBroadcasting broad) throws IOException{
		if(players.containsKey(clientId)){
			Player player = players.get(clientId);
			player.ready = true;
			broad.send(player.toMessageUpdate("ready"));
		}
	}
}
