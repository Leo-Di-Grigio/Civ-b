package script.userapi;

import misc.Enums;
import painter.Painter;
import tasks.Task;

public class api_MouseClicked extends ScriptApi {

	public static void execute(int x, int y) {
		Painter.currentScene.addTask(new Task(Enums.Task.MOUSE_CLICKED, ""+x+"."+y));
	}
}
