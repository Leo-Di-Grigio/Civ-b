package script.network;

import java.io.IOException;

import painter.Painter;
import tasks.Task;
import main.Config;
import misc.Enums;
import misc.Log;
import network.Message;
import network.Network;
import network.Message.Prefix;

public class netScripts {
	
	public static void createConnection() throws IOException{
		Log.debug("Execute net_CreateConnection");
		Network.createConnection(Config.serverAddress, Config.serverPort);
	}
	
	public static void disconnect() throws IOException{
		Network.disconnect();
	}
	
	public static void reciveMsg(Message msg) throws IOException {
		
		switch(msg.prefix){
		
			// normal connection
			case CONNECTION_OK:{
				Network.sendMsg(new Message(Prefix.REQUEST_GAMES_STATUS, null));
			} break;
			
			// connection err, not valid game version
			case CONNECTION_ERR:{
				Network.sendMsg(new Message(Prefix.DISCONNECT, null));
			} break;
			
			// games list in Hub
			case GAME_LIST:{
				Painter.currentScene.addTask(new Task(Enums.Task.NETWORK_GAMELIST, msg.data));
			} break;
			
			// join to the game
			case GAME_CONNECTION_SUCESS:{
				Painter.currentScene.addTask(new Task(Enums.Task.GAME_JOIN_SUCCESS, msg.data));
			} break;
			
			case GAME_CONNECTION_ERR:{
				Painter.currentScene.addTask(new Task(Enums.Task.GAME_JOIN_FAILED, msg.data));
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
