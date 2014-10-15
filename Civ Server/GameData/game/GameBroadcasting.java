package game;

import java.io.IOException;
import java.util.HashMap;

import net.Message;
import network.ClientPool;
import player.Player;

public class GameBroadcasting {
	
	protected HashMap<Integer, Player> players;
	
	public GameBroadcasting(HashMap<Integer, Player> players){
		this.players = players;
	}
	
	protected void send(Message msg) throws IOException{
		for(Integer clientId: players.keySet()){
			ClientPool.sendMsg(clientId , msg);
		}
	}
}
