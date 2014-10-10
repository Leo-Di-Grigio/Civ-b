package script.userapi;

import java.awt.event.KeyEvent;

import misc.Enums;
import painter.Painter;
import tasks.Task;

public class api_KeyPressed extends ScriptApi {

	public static void execute(KeyEvent e) {
		String data = KeyEvent.getKeyText(e.getKeyCode());
		Painter.currentScene.addTask(new Task(Enums.Task.KEYBOARD_PRESSED, data));
	}
}
