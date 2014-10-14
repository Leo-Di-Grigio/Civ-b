package script.userapi;

import misc.Enums;
import misc.Environment;
import painter.Painter;
import tasks.Task;

public class api_MouseMove extends ScriptApi {
	
	public static void execute(int x, int y){
		Environment.updateMousePosition(x, y);
		Painter.addTask(new Task(Enums.Task.MOUSE_MOVE, null));
	}
}
