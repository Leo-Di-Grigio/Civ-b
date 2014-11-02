package gameobject;

import net.Message;
import net.Message.Prefix;
import interfaces.Sentble;

abstract public class GameObject implements Sentble {

	// SERVER
	// ID
	private static int ID = 0;
	public int id;

	// params
	public int playerId;
	public int x;
	public int y;
	public int type;

	// turn
	public boolean turnEnd;

	public GameObject(int playerId, int type, int x, int y) {
		this.id = ID++;
		this.type = type;
		this.playerId = playerId;
		this.x = x;
		this.y = y;
	}

	@Override
	public Message toMessage() {
		String data = new String("");

		// write
		data += id + ":";
		data += playerId + ":";
		data += x + ":";
		data += y + ":";
		data += type + ":";

		return new Message(Prefix.OBJ_UNIT, data);
	}

	@Override
	public Message toMessageUpdate(String field) {
		String data = new String("");

		// 0-id, 1-fieldName, 2-field1, 3-field2
		data += id + ":" + field + ":";

		switch (field) {
			case "id":
				data += id;
				break;
				
			case "playerId":
				data += playerId;
				break;
				
			case "x":
				data += x;
				break;
				
			case "y":
				data += y;
				break;
				
			case "xy":
				data += x + ":" + y;
				break;
				
			case "type":
				data += type;
				break;
				
			case "clearway":
				break;
		}

		return new Message(Prefix.UPD_UNIT, data);
	}

	@Override
	public void buildObj(String[] data) {
	};

	@Override
	public void updateObj(String[] data) {
	};
}
