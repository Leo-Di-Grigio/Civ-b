package script.gui;

import painter.Painter;
import tasks.Task;
import misc.Enums;
import misc.Log;
import gui.GUI;

public class gui_ElementClick extends ScriptGui {

	public static void execute(GUI gui) {
		Log.debug("Åxecute gui_ElementClick");
		
		if(!gui.click()){
			Painter.currentScene.addTask(new Task(Enums.Task.SCENE_SELECTON, null));
		}
	}
}
