package tasks;

import java.util.ArrayList;

public class TasksPool {
	
	private ArrayList<Task> pool;
	
	public TasksPool(){
		pool = new ArrayList<Task>();
	}
	
	public synchronized void add(Task task){
		pool.add(task);
	}
	
	public synchronized Task [] getPool(){
		return pool.toArray(new Task [1]);
	}
	
	public synchronized Task poll(){
		return pool.get(0);
	}

	public synchronized void clear() {
		pool.clear();
	}
}
