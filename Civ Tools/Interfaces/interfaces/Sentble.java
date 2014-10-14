package interfaces;

import net.Message;

public interface Sentble {
	
	public Message toMessage();
	public Message toMessageUpdate(String field);
	public void buildObj(String data);
	public void updateObj(String data);
}
