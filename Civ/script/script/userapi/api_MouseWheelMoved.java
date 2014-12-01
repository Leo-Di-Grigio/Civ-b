package script.userapi;

import java.awt.event.MouseWheelEvent;

import misc.Enums;
import painter.Painter;
import tasks.Task;

public class api_MouseWheelMoved extends ScriptApi {

	public static void execute(MouseWheelEvent event) {
		Painter.addTask(new Task(Enums.Task.MOUSE_WHEEL, event));
	}
}