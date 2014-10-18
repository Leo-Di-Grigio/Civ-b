package player;

import net.Message;
import interfaces.Sentble;

public class Player implements Sentble {
	
	// CLIENT
	// ID
	public int id;
	
	// data
	public String name;
	public int teamId;
	public boolean ready;
	
	public Player(String data){
		String [] arr = data.split(":");
		buildObj(arr);
	}

	@Override
	public Message toMessage() {
		return null;
	}

	@Override
	public Message toMessageUpdate(String field) {
		return null;
	}

	@Override
	public void buildObj(String [] arr) {
		this.id = Integer.parseInt(arr[0]);
		this.name = arr[1];
		this.teamId = Integer.parseInt(arr[2]);
		this.ready = Boolean.parseBoolean(arr[3]);
	}

	@Override
	public void updateObj(String [] arr) {
		switch(arr[1]){
			case "id": id = Integer.parseInt(arr[2]); break;
			case "name": name = arr[2]; break;
			case "teamId": teamId = Integer.parseInt(arr[2]);
			case "ready": ready = Boolean.parseBoolean(arr[2]);
		}
	}
}
