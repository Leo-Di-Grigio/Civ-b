package script.gui;

import java.io.IOException;

import script.Script;
import tasks.Task;

public class ScriptGui extends Script {

	public ScriptGui() {
		super();
	}
	
	@Override
	public void execute(Task task) throws IOException{
		
	}
	
	@Override
	public Task preexecute(Task task, Object subscriberAddData) throws IOException {
		return task;
	}
}
