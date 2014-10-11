package network;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected long timestamp;
	protected String prefix;
	protected String data;
	
	public Message(String prefix, String data) {
		this.timestamp = System.currentTimeMillis();
		this.prefix = prefix;
		this.data = data;
	}
}
