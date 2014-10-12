package network;

import java.io.IOException;
import java.util.HashMap;

public class ClientPool {

	protected static HashMap<Integer, Client> clients;
	
	public ClientPool() {
		clients = new HashMap<Integer, Client>();
	}
	
	public static void add(Client client){
		clients.put(client.id, client);
		new Thread(client).start();
	}
	
	public static void remove(int id){
		if(clients.containsKey(id)){
			clients.remove(id);
		}
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
			clients.get(id).send(new Message("debug", info));
		}
	}
}
