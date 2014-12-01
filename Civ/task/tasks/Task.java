package tasks;

import gui.GUI;
import misc.Const;
import misc.Enums;

public class Task {

	private static long ID;
	public long id;
	
	public Enums.Task type;
	public Object data;
	public GUI sceneGui;

	public boolean blocked;
	
	public Task(Enums.Task type, Object data) {
		this.id = ID++;
		this.blocked = false;
		this.type = type;
		this.data = data;
		
		if(ID % Const.tasksForGC == 0){
			System.gc();
		}
	}
	
	public Task(Enums.Task type, Object data, GUI sceneGui){
		this(type, data);
		this.sceneGui = sceneGui;
	}
}
