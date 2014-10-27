package network;

import java.io.IOException;

import painter.Painter;
import misc.Enums;
import net.Message;
import net.Message.Prefix;

public class Network {
	
	protected static Connection connect;
	
	public static void createConnection(String ip, int port) throws IOException{
		connect = new Connection(ip, port);
		new Thread(connect).start();
	}
	
	public static void disconnect() throws IOException{
		if(connect != null){
			if(connect.socket != null && connect.socket.isBound()){
				sendMsg(new Message(Prefix.REQ_DISCONNECT, null));
			}
			else{
				if(Painter.currentSceneTitle == Enums.Scene.PREPEARE){
					sendMsg(new Message(Prefix.REQ_GAMES_LIST, null));
				}
			}
		}
	}

	public static void removeConnection() {
		connect = null;
		System.gc();
	}
	
	public static boolean connectionExist(){
		if(connect != null && connect.socket != null && connect.socket.isBound()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public synchronized static void sendMsg(Message msg) throws IOException{
		connect.send(msg);
	}

	public static void sendMsg(Prefix chatMsg, String userText) {
		
	}
}
