package gamedata;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import misc.Log;
import net.Message;
import net.Message.Prefix;
import network.Client;
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
	
	public int add(int clientId, String playerName, long gameSeed, int sizeX, int sizeY, int tMin, int tMax, int landPercent, GameBroadcasting broad) throws IOException{
		if(list.size() < playersMax){
			if(list.containsKey(clientId)){
				Log.err("Player already in game");
				ClientPool.sendMsg(clientId, new Message(Prefix.GAME_JOIN_ERR, "already played"));
				return -1;
			}
			else{
				Player player = new Player(clientId, playerName);
				list.put(clientId, player);
				Client client = ClientPool.getClient(clientId);
						
				if(client != null){
					client.setGameId(gameId);
				}
				
				// broad data
				broad.sendToPlayers(player.toMessage());
				
				// send data
				String dataGame = gameSeed + ":" + sizeX + ":" + sizeY + ":" + clientId + ":" + tMin + ":" + tMax + ":" + landPercent;
				ClientPool.sendMsg(clientId, new Message(Prefix.DATA_GAME, dataGame));
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
	
	public void sendMessageToPlayer(int playerId, Message msg) throws IOException{
		ClientPool.sendMsg(playerId, msg);
	}

	public void sendMessageToAllPlayers(Message msg) throws IOException {
		for(Integer clientId: list.keySet()){
			ClientPool.sendMsg(clientId , msg);
		}
	}

	public void sendMessageToAllTeams(HashSet<Integer> players, Message msg) throws IOException {
		for(Integer clientId: players){
			ClientPool.sendMsg(clientId, msg);
		}
	}
}
