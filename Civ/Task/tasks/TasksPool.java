package tasks;

import java.util.LinkedList;

public class TasksPool {
	
	private boolean swap = true;
	private LinkedList<Task> list1;
	private LinkedList<Task> list2;
	
	public TasksPool(){
		list1 = new LinkedList<Task>();
		list2 = new LinkedList<Task>();
	}
	
	public void add(Task e){
		if(swap){
			list2.add(e);
		}
		else{
			list1.add(e);
		}
	}
	
	public boolean hasNext(){
		if(swap){
			if(list1.size() > 0){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(list2.size() > 0){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public Task poll(){
		if(swap){
			return list1.poll();
		}
		else{
			return list2.poll();
		}
	}

	public void clear() {
		if(swap){
			list1.clear();
		}
		else{
			list2.clear();
		}
	}

	public void swap() {
		swap = !swap;
	}
}
