package script.network;

import java.io.IOException;

import painter.Painter;
import tasks.Task;
import misc.Enums;
import misc.Log;
import network.Message;
import network.Network;
import network.Message.Prefix;


public class net_ReciveMsg extends ScriptNetwork {

	public static void execute(Message msg) throws IOException {
		
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
			
			case DEBUG:{
				Log.debug("MSG: " + msg.prefix + ":" + msg.data);
			} break;
			
			default:{
				Log.debug("MSG: " + msg.data);
			} break;
		}
	}
}
