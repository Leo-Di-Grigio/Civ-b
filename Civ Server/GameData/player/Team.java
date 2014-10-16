package player;

import net.Message;
import net.Message.Prefix;
import interfaces.Sentble;

public class Team implements Sentble  {
	
	// SERVER
	// ID
	private static int ID = 0;
	public int id;
	
	// data
	public String name;
	public int ownerPlayerId;
	
	public Team(int playerId, String name) {
		this.id = ID++;
		this.name = name;
		this.ownerPlayerId = playerId;
	}

	@Override
	public Message toMessage() {
		String data = new String("");
		
		data += id 	 + ":";
		data += name + ":";
		data += ownerPlayerId + ":";
		
		return new Message(Prefix.OBJ_TEAM, data);
	}

	@Override
	public Message toMessageUpdate(String field) {
		String data = new String("");
		
		data += field + ":";
		
		switch(field){
			case "id": 	 data += id; break;
			case "name": data += name; break;
			case "ownerPlayerId": data += ownerPlayerId; break;
		}
		
		return new Message(Prefix.UPD_TEAM, data);
	}

	@Override
	public void buildObj(String data) {
		
	}

	@Override
	public void updateObj(String data) {
		
	}
}
