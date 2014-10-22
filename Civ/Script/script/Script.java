package script;

import java.io.IOException;

import tasks.Task;

abstract public class Script {
	
	public void execute(Task task) throws IOException{
		
	}
	
	public Task preexecute(Task task, Object addData) throws IOException{
		return task;
	}
}
