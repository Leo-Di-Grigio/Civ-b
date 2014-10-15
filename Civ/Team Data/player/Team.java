package player;

import net.Message;
import interfaces.Sentble;

public class Team implements Sentble {
	
	// CLIENT
	// ID
	public int id;
	
	// data
	public String name;
	
	public Team(int id, String name) {
		this.id = id;
		this.name = name;
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
		
		this.id = Integer.getInteger(arr[0]);
		this.name = arr[1];
	}

	@Override
	public void updateObj(String data) {
		String [] arr = data.split(":");
		
		switch(arr[0]){
			case "id": id = Integer.getInteger(arr[1]); break;
			case "name": name = arr[1]; break;
		}
	}
}