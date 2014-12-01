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
			case CONNECTION_OK:
				Network.sendMsg(new Message(Prefix.REQ_GAMES_LIST, null));
				break;
			
			// connection err, not valid game version
			case CONNECTION_ERR:
				Network.sendMsg(new Message(Prefix.REQ_DISCONNECT, null));
				break;
			
			// games list in Hub
			case DATA_GAMES_LIST:
				Painter.addTask(new Task(Enums.Task.DATA_GAMELIST, msg.data));
				break;
			
			// join to the game
			case DATA_GAME:
				Painter.addTask(new Task(Enums.Task.GAME_JOIN_SUCCESS, msg.data));
				break;
			
			case GAME_JOIN_ERR:
				Painter.addTask(new Task(Enums.Task.GAME_JOIN_FAILED, msg.data));
				break;
			
			case DEBUG:
				Log.debug("MSG: " + msg.prefix + ":" + msg.data);
				break;
			
			// Data objects
			case OBJ_PLAYER:
				Painter.addTask(new Task(Enums.Task.GAME_OBJ_PLAYER, msg.data));
				break;
			
			case OBJ_TEAM:
				Painter.addTask(new Task(Enums.Task.GAME_OBJ_TEAM, msg.data));
				break;
			
			case OBJ_UNIT:
				Painter.addTask(new Task(Enums.Task.GAME_OBJ_UNIT, msg.data));
				break;
			
			case OBJ_IVENTORY:
				Painter.addTask(new Task(Enums.Task.GAME_OBJ_INVENTORY, msg.data));
				break;
				
			// Data update
			case UPD_PLAYER:
				Painter.addTask(new Task(Enums.Task.GAME_UPD_PLAYER, msg.data));
				break;
			
			case UPD_TEAM:
				Painter.addTask(new Task(Enums.Task.GAME_UPD_TEAM, msg.data));
				break;
			
			case UPD_UNIT:
				Painter.addTask(new Task(Enums.Task.GAME_UPD_UNIT, msg.data));
				break;
				
			case UPD_INVENTORY:
				Painter.addTask(new Task(Enums.Task.GAME_UPD_INVENTORY, msg.data));
				break;
			
			// Data del
			case DEL_PLAYER:
				Painter.addTask(new Task(Enums.Task.GAME_DEL_PLAYER, msg.data));
				break;
			
			case DEL_TEAM:
				Painter.addTask(new Task(Enums.Task.GAME_DEL_TEAM, msg.data));
				break;
			
			case DEL_UNIT:
				Painter.addTask(new Task(Enums.Task.GAME_DEL_UNIT, msg.data));
				break;
			
			case GAME_BEGIN:
				Painter.addTask(new Task(Enums.Task.GAME_BEGIN, msg.data));
				break;
			
			case PLAYER_ACTION:
				Painter.addTask(new Task(Enums.Task.PLAYER_ACTION, msg.data));
				break;
			
			case GAME_TURN:
				Painter.addTask(new Task(Enums.Task.GAME_TURN, msg.data));
				break;
			
			case CHAT_MSG:
				Painter.addTask(new Task(Enums.Task.CHAT_MSG, msg.data));
				break;
			
			default:
				Log.debug("MSG: " + msg.data);
				break;
		}
	}
}
