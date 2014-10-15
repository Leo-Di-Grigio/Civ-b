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
	
	public Unit(String data){
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
		
		// read
		this.id = Integer.parseInt(arr[0]);
		this.playerId = Integer.parseInt(arr[1]);
		this.x = Integer.parseInt(arr[2]);
		this.y = Integer.parseInt(arr[3]);
		this.type = Integer.parseInt(arr[4]);
	}

	@Override
	public void updateObj(String data) {
		String [] arr = data.split(":");
		
		switch(arr[0]){
			case "id": 		 id = Integer.parseInt(arr[1]); break;
			case "playerId": playerId = Integer.parseInt(arr[1]); break;
			case "x": 		 x = Integer.parseInt(arr[1]); break;
			case "y": 		 y = Integer.parseInt(arr[1]); break;
			case "type": 	 type = Integer.parseInt(arr[1]); break;
		}
	}
}
