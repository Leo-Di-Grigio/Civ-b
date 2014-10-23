package task;

import game.GamesMng;

import java.io.IOException;

import misc.Const;
import net.Message;
import net.Message.Prefix;
import network.Client;
import network.ClientPool;

public class TaskLogic {
	
	public static void pushTask(Task task) throws IOException{
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
			
			case REQ_GAME_DATA:
				requestGameData(task);
				break;
			
			case REQ_TEAM_CREATE:
				requestTeamCreate(task);
				break;
		
			case REQ_TEAM_CHOOSE:
				requestTeamChoose(task);
				break;
			
			case REQ_READY_CHECK:
				requestReadyCheck(task);
				break;
				
			case PLAYER_ACTION:
				playerAction(task);
				break;
		
			default: break;
		}
	}

	private static void disconnect(Task task) throws IOException {
		Client client = ClientPool.remove(task.clientId);
		
		if(client != null && client.gameId != -1){
			GamesMng.get(client.gameId).logic.removePlayer(task.clientId);
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
	
	private static void requestGameStatus(Task task) throws IOException{
		ClientPool.sendMsg(task.clientId, new Message(Prefix.DATA_GAMES_LIST, GamesMng.getList()));
	}
	
	private static void requestGameJoin(Task task) throws IOException{
		String [] arr = task.msg.data.split(":");
		
		if(arr != null && arr.length == 2){
			int gameId = Integer.valueOf(arr[0]);
			String playerName = arr[1];
			GamesMng.get(gameId).logic.addPlayer(task.clientId, playerName);
		}
		else{
			ClientPool.sendMsg(task.clientId, new Message(Prefix.GAME_JOIN_ERR, "no game selected or no playerName"));
		}
	}
	
	private static void requestGameLeave(Task task) throws IOException {
		int gameId = ClientPool.getClient(task.clientId).gameId;
		GamesMng.get(gameId).logic.removePlayer(task.clientId);
	}

	private static void requestGameData(Task task) throws IOException {
		int gameId = ClientPool.getClient(task.clientId).gameId;
		GamesMng.get(gameId).logic.sendGameData(task.clientId);
	}
	
	private static void requestTeamCreate(Task task) throws IOException {
		int gameId = ClientPool.getClient(task.clientId).gameId;
		GamesMng.get(gameId).logic.addTeam(task.clientId, task.msg.data);
	}
	
	private static void requestTeamChoose(Task task) throws IOException {
		int gameId = ClientPool.getClient(task.clientId).gameId;
		GamesMng.get(gameId).logic.teamChoose(task.clientId, task.msg.data);
	}
	
	private static void requestReadyCheck(Task task) throws IOException {
		int gameId = ClientPool.getClient(task.clientId).gameId;
		GamesMng.get(gameId).logic.playerReadyCheck(task.clientId);
	}
	
	private static void playerAction(Task task) throws IOException {
		int gameId = ClientPool.getClient(task.clientId).gameId;
		GamesMng.get(gameId).logic.playerAcrion(task.clientId, task.msg.data);
	}
}
