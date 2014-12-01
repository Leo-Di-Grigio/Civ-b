package task;

import net.Message;

public class Task {
	
	public int clientId;
	public Message msg;
	
	public Task(int clientId, Message msg){
		this.clientId = clientId;
		this.msg = msg;
	}
}
