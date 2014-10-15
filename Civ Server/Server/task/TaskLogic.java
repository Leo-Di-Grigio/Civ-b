package task;

import game.GamesMng;

import java.io.IOException;

import misc.Const;
import net.Message;
import net.Message.Prefix;
import network.ClientPool;

public class TaskLogic {
	
	public static void pushTask(Task task) throws IOException{
		if(task != null){			
			
			switch(task.msg.prefix){			
				
				case CHECK_VERSION:
					checkVersion(task);
					break;
				
				case REQ_DISCONNECT:
					disconnect(task);
					break;
				
				case REQ_GAMES_LIST:
					requestGameStatus(task);
					break;
				
				case REQ_GAME_JOIN:
					requestGameJoin(task);
					break;
				
				case REQ_GAME_LEAVE:
					requestGameLeave(task);
					break;
				
				default: break;
			}
		}
	}

	private static void checkVersion(Task task) throws IOException{
		if(task.msg.data.compareTo(""+Const.version+"."+Const.subVersion) == 0){
			ClientPool.sendMsg(task.clientId, new Message(Prefix.CONNECTION_OK, null));
		}
		else{
			ClientPool.sendMsg(task.clientId, new Message(Prefix.CONNECTION_ERR, ""+Const.version+"."+Const.subVersion));
		}
	}
	
	private static void disconnect(Task task) throws IOException{
		int gameId = ClientPool.getClient(task.clientId).gameId;
		if(gameId != -1){
			GamesMng.get(gameId).removePlayer(task.clientId);
		}
		ClientPool.remove(task.clientId);
	}
	
	private static void requestGameStatus(Task task) throws IOException{
		ClientPool.sendMsg(task.clientId, new Message(Prefix.DATA_GAMES_LIST, GamesMng.getList()));
	}
	
	private static void requestGameJoin(Task task) throws IOException{
		String [] arr = task.msg.data.split(":");
		
		if(arr != null && arr.length == 2){
			int gameId = Integer.valueOf(arr[0]);
			String playerName = arr[1];
			GamesMng.get(gameId).addPlayer(task.clientId, playerName);
		}
		else{
			ClientPool.sendMsg(task.clientId, new Message(Prefix.GAME_JOIN_ERR, "no game selected or no playerName"));
		}
	}
	
	private static void requestGameLeave(Task task) {
		int gameId = ClientPool.getClient(task.clientId).gameId;
		GamesMng.get(gameId).removePlayer(task.clientId);
	}
}
