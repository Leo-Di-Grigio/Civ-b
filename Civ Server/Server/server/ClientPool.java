package server;

public class ClientPool {

	public ClientPool() {
		
	}
	
	public void add(Client client){
		new Thread(client).start();
	}
}
