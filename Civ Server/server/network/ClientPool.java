package network;

import java.io.IOException;
import java.util.HashMap;

import net.Message;
import net.Message.Prefix;

public class ClientPool {

	protected static HashMap<Integer, Client> clients;
	
	public ClientPool() {
		clients = new HashMap<Integer, Client>();
	}
	
	public static void add(Client client){
		clients.put(client.clientId, client);
		new Thread(client).start();
	}
	
	public static Client remove(int id) throws IOException{
		return clients.remove(id);
	}
	
	public static Client getClient(int id){
		return clients.get(id);
	}
	
	public static void sendToAll(Message msg) throws IOException{
		for(Client client: clients.values()){
			client.send(msg);
		}
	}
	
	public static void sendMsg(int id, Message msg) throws IOException {
		if(clients.containsKey(id)){
			clients.get(id).send(msg);
		}
	}
	
	public static void sendDebugMsg(int id, String info) throws IOException{
		if(clients.containsKey(id)){
			clients.get(id).send(new Message(Prefix.DEBUG, info));
		}
	}
}
