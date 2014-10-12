package network;

import game.GameList;

import java.io.IOException;
import java.util.LinkedList;

import network.Message.Prefix;
import misc.ToolsConst;

public class TaskPool {
	
	protected static LinkedList<Task> list;
	protected static boolean pause = true;
	
	public TaskPool(){
		list = new LinkedList<Task>();
	}
	
	public static void add(Task task) throws IOException{
		list.add(task);
		
		if(pause){
			pause = false;
			execute();
		}
	}
	
	public static void clear(){
		list.clear();
		System.gc();
	}

	private static boolean hasNext(){
		if(list.size() > 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	private static Task pool(){
		return list.poll();
	}
	
	private static void execute() throws IOException{
		while(hasNext()){
			execute(pool());
		}
		pause = true;
	}
	
	private static void execute(Task task) throws IOException{
		if(task != null){			
			switch(task.msg.prefix){			
				
				// check version
				case CHECK_VERSION:{
					if(task.msg.data.compareTo(""+ToolsConst.version+"."+ToolsConst.subVersion) == 0){
						ClientPool.sendMsg(task.clientId, new Message(Prefix.CONNECTION_OK, null));
					}
					else{
						ClientPool.sendMsg(task.clientId, new Message(Prefix.CONNECTION_ERR, ""+ToolsConst.version+"."+ToolsConst.subVersion));
					}
				} break;
					
				// request disconnect
				case DISCONNECT:{
					ClientPool.remove(task.clientId);
				} break;
				
				// request game status
				case REQUEST_GAMES_STATUS:{
					ClientPool.sendMsg(task.clientId, new Message(Prefix.GAME_LIST, GameList.getList()));
				} break;
				
				case REQUEST_GAME_CONNECTION:{
					int gameId = Integer.valueOf(task.msg.data);
					GameList.get(gameId).addPlayer(task.clientId);
				} break;
				
				default: break;
			}
		}
	}
}
