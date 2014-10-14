package script.userapi;

import misc.Enums;
import painter.Painter;
import tasks.Task;

public class api_MouseReleased extends ScriptApi {

	public static void execute(int x, int y) {
		Painter.addTask(new Task(Enums.Task.MOUSE_RELEASED, ""+x+"."+y));
	}
}
