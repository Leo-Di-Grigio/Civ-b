package script.gui;

import java.io.IOException;

import painter.Painter;
import tasks.Task;
import misc.Enums;
import misc.Log;
import gui.GUI;

public class gui_ElementClick extends ScriptGui {

	public static void execute(GUI gui) throws IOException {
		Log.debug("Åxecute gui_ElementClick");
		
		if(!gui.click()){
			Painter.currentScene.addTask(new Task(Enums.Task.SCENE_SELECTON, null));
		}
	}
}
