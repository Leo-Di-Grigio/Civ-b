package player.units;

import net.Message;
import interfaces.Sentble;

public class Unit implements Sentble{
	
	// CLIENT
	// id
	public int id;
	
	// params
	public int playerId;
	public int x;
	public int y;
	public int type;
	
	public Unit(int id, int playerId, int x, int y) {
		this.id = id;
		this.playerId = playerId;
		this.x = x;
		this.y = y;
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
		
		// read
		this.id = Integer.getInteger(arr[0]);
		this.playerId = Integer.getInteger(arr[1]);
		this.x = Integer.getInteger(arr[2]);
		this.y = Integer.getInteger(arr[3]);
		this.type = Integer.getInteger(arr[4]);
	}

	@Override
	public void updateObj(String data) {
		String [] arr = data.split(":");
		
		switch(arr[0]){
			case "id": 		 id = Integer.getInteger(arr[1]); break;
			case "playerId": playerId = Integer.getInteger(arr[1]); break;
			case "x": 		 x = Integer.getInteger(arr[1]); break;
			case "y": 		 y = Integer.getInteger(arr[1]); break;
			case "type": 	 type = Integer.getInteger(arr[1]); break;
		}
	}
}
