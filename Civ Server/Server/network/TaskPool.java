package network;

import game.GameList;
import java.io.IOException;
import java.util.LinkedList;
import misc.Const;

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
				case "checkVersion":{
					if(task.msg.data.compareTo(""+Const.version+"."+Const.subVersion) == 0){
						ClientPool.sendMsg(task.clientId, new Message("connection_ok", null));
					}
					else{
						ClientPool.sendMsg(task.clientId, new Message("connection_err", ""+Const.version+"."+Const.subVersion));
					}
				} break;
					
				// request disconnect
				case "disconnect":{
					ClientPool.remove(task.clientId);
				} break;
				
				// request game status
				case "requestGamesStatus":{
					ClientPool.sendMsg(task.clientId, new Message("gamesList", GameList.getList()));
				} break;
				
				default: break;
			}
		}
	}
}
