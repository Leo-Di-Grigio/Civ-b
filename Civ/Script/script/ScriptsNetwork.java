package script;

import java.io.IOException;

import painter.Painter;
import tasks.Task;
import main.Config;
import misc.Enums;
import misc.Log;
import net.Message;
import net.Message.Prefix;
import network.Network;

public class ScriptsNetwork {
	
	public static void createConnection() throws IOException{
		Log.debug("Execute net_CreateConnection");
		
		if(!Network.connectionExist()){
			Network.createConnection(Config.serverAddress, Config.serverPort);
		}
	}
	
	public static void disconnect() throws IOException{
		Network.disconnect();
	}
	
	public static void reciveMsg(Message msg) throws IOException {
		
		switch(msg.prefix){
		
			// normal connection
			case CONNECTION_OK:{
				Network.sendMsg(new Message(Prefix.REQ_GAMES_LIST, null));
			} break;
			
			// connection err, not valid game version
			case CONNECTION_ERR:{
				Network.sendMsg(new Message(Prefix.REQ_DISCONNECT, null));
			} break;
			
			// games list in Hub
			case DATA_GAMES_LIST:{
				Painter.addTask(new Task(Enums.Task.NETWORK_GAMELIST, msg.data));
			} break;
			
			// join to the game
			case DATA_GAME:{
				Painter.addTask(new Task(Enums.Task.GAME_JOIN_SUCCESS, msg.data));
			} break;
			
			case GAME_JOIN_ERR:{
				Painter.addTask(new Task(Enums.Task.GAME_JOIN_FAILED, msg.data));
			} break;
			
			case DEBUG:{
				Log.debug("MSG: " + msg.prefix + ":" + msg.data);
			} break;
			
			default:{
				Log.debug("MSG: " + msg.data);
			} break;
		}
	}
}
