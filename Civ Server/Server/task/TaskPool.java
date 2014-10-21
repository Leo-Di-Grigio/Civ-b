package task;

import java.io.IOException;
import java.util.LinkedList;

public class TaskPool {
	
	protected static LinkedList<Task> list;
	protected static boolean pause = true;
	
	public TaskPool(){
		list = new LinkedList<Task>();
	}
	
	public synchronized static void add(Task task) throws IOException{
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
			TaskLogic.pushTask(pool());
		}
		pause = true;
	}
}
