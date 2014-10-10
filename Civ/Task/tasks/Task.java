package tasks;

import misc.Const;
import misc.Enums;

public class Task {

	private static long ID;
	public long id;
	
	public Enums.Task type;
	public Object data;
	
	public Task(Enums.Task type, Object data) {
		this.id = ID++;
		this.type = type;
		this.data = data;
		
		if(ID % Const.tasksForGC == 0){
			System.gc();
		}
	}
}
