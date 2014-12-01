package task;

import game.GamesMng;

import java.io.IOException;

import misc.ToolsConst;
import net.Message;
import net.Message.Prefix;
import network.Client;
import network.ClientPool;

public class TaskLogic {
	
	public static void pushTask(Task task) throws Throwable{
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
				
			case GAME_TURN_END:
				gameTurnEnd(task);
				break;
			
			case CHAT_MSG:
				chatMsg(task);
				break;
				
			default: break;
		}
	}

	private static void disconnect(Task task) throws Throwable {
		Client client = ClientPool.remove(task.clientId);
		
		if(client != null){
			if(client.gameId != -1){
				GamesMng.get(client.gameId).gamedata.removePlayer(task.clientId);
			}
			
			client.disconnect();
		}
	}
	
	private static void checkVersion(Task task) throws IOException{
		if(task.msg.data.compareTo(""+ToolsConst.version+"."+ToolsConst.subVersion) == 0){
			ClientPool.sendMsg(task.clientId, new Message(Prefix.CONNECTION_OK, null));
		}
		else{
			ClientPool.sendMsg(task.clientId, new Message(Prefix.CONNECTION_ERR, ""+ToolsConst.version+"."+ToolsConst.subVersion));
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
			GamesMng.get(gameId).gamedata.addPlayer(task.clientId, playerName);
		}
		else{
			ClientPool.sendMsg(task.clientId, new Message(Prefix.GAME_JOIN_ERR, "no game selected or no playerName"));
		}
	}
	
	private static int getGameId(Task task){
		return ClientPool.getClient(task.clientId).gameId;
	}
	
	private static void requestGameLeave(Task task) throws IOException {
		GamesMng.get(getGameId(task)).gamedata.removePlayer(task.clientId);
	}

	private static void requestGameData(Task task) throws IOException {
		GamesMng.get(getGameId(task)).gamedata.sendGameData(task.clientId);
	}
	
	private static void requestTeamCreate(Task task) throws IOException {
		GamesMng.get(getGameId(task)).gamedata.addTeam(task.clientId, task.msg.data);
	}
	
	private static void requestTeamChoose(Task task) throws IOException {
		GamesMng.get(getGameId(task)).gamedata.teamChoose(task.clientId, task.msg.data);
	}
	
	private static void requestReadyCheck(Task task) throws IOException {
		GamesMng.get(getGameId(task)).gamedata.playerReadyCheck(task.clientId);
	}
	
	private static void playerAction(Task task) throws IOException {
		GamesMng.get(getGameId(task)).gamedata.addPlayerAction(task.clientId, task.msg.data);
	}
	
	private static void gameTurnEnd(Task task) throws IOException {
		GamesMng.get(getGameId(task)).gamedata.gameTurnEnd(task.clientId);
	}
	
	private static void chatMsg(Task task) throws IOException {
		GamesMng.get(getGameId(task)).gamedata.gameChat(task.clientId, task.msg.data);
	}
}
