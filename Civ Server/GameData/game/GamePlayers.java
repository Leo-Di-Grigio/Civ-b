package game;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import misc.Log;
import net.Message;
import net.Message.Prefix;
import network.ClientPool;
import player.Player;

public class GamePlayers {
	
	// id
	protected int gameId;
	
	// params
	protected int playersMax;
	
	// data
	private HashMap<Integer, Player> list;
	
	public GamePlayers(int gameId, int playersMax){
		this.gameId = gameId;
		this.playersMax = playersMax;
		this.list = new HashMap<Integer, Player>();
	}
	
	public Player get(int playerId) {
		return list.get(playerId);
	}
	
	public int size(){
		return list.size();
	}
	
	public int add(int clientId, String playerName, long gameSeed, int sizeX, int sizeY, GameBroadcasting broad) throws IOException{
		if(list.size() < playersMax){
			if(list.containsKey(clientId)){
				Log.err("Player already in game");
				ClientPool.sendMsg(clientId, new Message(Prefix.GAME_JOIN_ERR, "already played"));
				return -1;
			}
			else{
				Player player = new Player(clientId, playerName);
				list.put(clientId, player);
				ClientPool.getClient(clientId).setGameId(gameId);
				
				// broad data
				broad.sendToPlayers(player.toMessage());
				
				// send data
				ClientPool.sendMsg(clientId, new Message(Prefix.DATA_GAME, "" + gameSeed + ":" + sizeX + ":" + sizeY + ":" + clientId));
				return player.id;
			}
		}
		else{
			ClientPool.sendMsg(clientId, new Message(Prefix.GAME_JOIN_ERR, "server full"));
			return -1;
		}
	}
	
	public Player remove(int clientId, GameBroadcasting broad) throws IOException{
		return list.remove(clientId);
	}
	
	public void sendPlayesList(int clientId) throws IOException{
		for(Player item: list.values()){
			ClientPool.sendMsg(clientId, item.toMessage());
		}
	}
	
	public boolean readyCheckPlayer(int clientId, GameBroadcasting broad) throws IOException{
		if(list.containsKey(clientId)){
			Player player = list.get(clientId);
			player.ready = true;
			broad.sendToPlayers(player.toMessageUpdate("ready"));
		}
		
		for(Player player: list.values()){
			if(!player.ready){
				return false;
			}
		}
		
		return true;
	}

	public void setMessageToAllPlayers(Message msg) throws IOException {
		for(Integer clientId: list.keySet()){
			ClientPool.sendMsg(clientId , msg);
		}
	}

	public void setMessageToAllTeams(HashSet<Integer> players, Message msg) throws IOException {
		for(Integer clientId: players){
			ClientPool.sendMsg(clientId, msg);
		}
	}
}
