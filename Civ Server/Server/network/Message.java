package network;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static enum Prefix {
		// connection control
		CONNECTION_OK,
		CONNECTION_ERR,
		DISCONNECT,
		
		// version
		CHECK_VERSION,
		
		// requests
		REQUEST_GAMES_STATUS,
		REQUEST_GAME_CONNECTION,
		
		// data
		GAME_LIST,
		
		// game connection
		GAME_CONNECTION_ERR,
		GAME_CONNECTION_SUCESS,
		
		// other
		DEBUG;
	};
	
	public long timestamp;
	public Prefix prefix;
	public String data;
	
	public Message(Prefix prefix, String data) {
		this.timestamp = System.currentTimeMillis();
		this.prefix = prefix;
		this.data = data;
	}
}
