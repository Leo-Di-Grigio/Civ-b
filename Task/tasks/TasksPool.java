package tasks;

import java.util.LinkedList;

public class TasksPool {
	
	private LinkedList<Task> list;
	
	public TasksPool(){
		list = new LinkedList<Task>();
	}
	
	public void add(Task e){
		list.add(e);
	}
	
	public boolean hasNext(){
		if(list.size() > 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Task poll(){
		return list.poll();
	}

	public void clear() {
		list.clear();
		System.gc();
	}
}
