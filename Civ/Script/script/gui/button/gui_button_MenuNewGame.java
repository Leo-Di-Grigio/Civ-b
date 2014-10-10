package script.gui.button;

import painter.Painter;
import misc.Enums;
import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_MenuNewGame extends ScriptGui {
	
	@Override
	public void execute(Task task){
		Log.debug("Execute gui_button_MenuNewGame");
		Painter.currentScene.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, "menu_newGame"));
	}
}
