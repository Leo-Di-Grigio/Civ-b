package game;

import java.io.IOException;
import net.Message;
import network.ClientPool;

public class GameBroadcasting {
	
	protected GamePlayers players;
	
	public GameBroadcasting(GamePlayers players){
		this.players = players;
	}
	
	protected void send(Message msg) throws IOException{
		for(Integer clientId: players.players.keySet()){
			ClientPool.sendMsg(clientId , msg);
		}
	}
}
