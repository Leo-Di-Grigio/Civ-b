package network;

import java.io.IOException;

public class Network {

	protected static Connection connect;

	public static void createConnection(String ip, int port) throws IOException{
		connect = new Connection(ip, port);
		new Thread(connect).start();
	}
	
	public static void disconnect() throws IOException{
		if(connect != null){
			if(connect.socket != null && connect.socket.isBound()){
				sendMsg(new Message("disconnect", null));
			}
		}
	}
	
	public synchronized static void sendMsg(Message msg) throws IOException{
		connect.send(msg);
	}
}
