package player;

import interfaces.Sentble;
import net.Message;
import net.Message.Prefix;

public class Player implements Sentble {
	
	// SERVER
	// ID
	public static int ID = 0;
	public int id;
	
	// data
	public String name;
	
	public Player(String name) {
		this.id = ID++;
		this.name = name;
	}
	
	@Override
	public Message toMessage() {
		String data = new String("");
		
		data += id 	 + ":";
		data += name + ":";
		
		return new Message(Prefix.OBJ_PLAYER, data);
	}

	@Override
	public Message toMessageUpdate(String field) {
		String data = new String("");
		
		data += field + ":";
		
		switch(field){
			case "id": 	 data += id; break;
			case "name": name += name; break;
		}
		
		return new Message(Prefix.UPD_PLAYER, data);
	}

	@Override
	public void buildObj(String data) {
		
	}

	@Override
	public void updateObj(String data) {
		
	}
}
