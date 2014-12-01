package player;

import interfaces.Sentble;
import net.Message;
import net.Message.Prefix;

public class Player implements Sentble {
	
	// SERVER
	// ID
	public int id;
	
	// data
	public String name;
	public int teamId;
	public boolean ready;
	
	public Player(int clientId, String name) {
		this.id = clientId;
		this.name = name;
		this.teamId = 0;
		this.ready = false;
	}
	
	@Override
	public Message toMessage() {
		String data = new String("");
		
		data += id 	 + ":";
		data += name + ":";
		data += teamId + ":";
		data += ready + ":";
		
		return new Message(Prefix.OBJ_PLAYER, data);
	}

	@Override
	public Message toMessageUpdate(String field) {
		String data = new String("");
		
		data += id + ":" + field + ":";
		
		switch(field){
			case "name": data += name; break;
			case "teamId": data += teamId; break;
			case "ready": data += ready; break;
		}
		
		return new Message(Prefix.UPD_PLAYER, data);
	}

	@Override
	public void buildObj(String [] data) {};
	@Override
	public void updateObj(String [] data) {};
}
