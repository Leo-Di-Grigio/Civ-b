package task;

import java.util.LinkedList;

public class TaskPool {
	
	protected static LinkedList<Task> list;
	protected static boolean pause = true;
	
	public TaskPool(){
		list = new LinkedList<Task>();
	}
	
	public synchronized static void add(Task task) throws Throwable{
		list.add(task);
		
		if(pause){
			pause = false;
			execute();
		}
	}
	
	public static void clear(){
		list.clear();
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
	
	private static void execute() throws Throwable{
		while(hasNext()){
			TaskLogic.pushTask(pool());
		}
		pause = true;
	}
}
