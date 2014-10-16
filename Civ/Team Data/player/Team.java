package player;

import net.Message;
import interfaces.Sentble;

public class Team implements Sentble {
	
	// CLIENT
	// ID
	public int id;
	
	// data
	public String name;
	public int ownerPlayerId;
	
	public Team(String data){
		buildObj(data);
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
	public void buildObj(String data) {
		String [] arr = data.split(":");
		
		this.id = Integer.parseInt(arr[0]);
		this.name = arr[1];
		this.ownerPlayerId = Integer.parseInt(arr[2]);
	}

	@Override
	public void updateObj(String data) {
		String [] arr = data.split(":");
		
		switch(arr[0]){
			case "id": id = Integer.parseInt(arr[1]); break;
			case "name": name = arr[1]; break;
			case "ownerPlayerId": ownerPlayerId = Integer.parseInt(arr[1]); break;
		}
	}
}
