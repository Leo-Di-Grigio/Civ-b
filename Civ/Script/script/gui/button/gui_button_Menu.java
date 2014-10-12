package script.gui.button;

import misc.Enums;
import misc.Log;
import painter.Painter;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_Menu extends ScriptGui {
	
	@Override
	public void execute(Task task){
		Log.debug("Execute gui_button_MenuExit");
		Painter.currentScene.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, Enums.Scene.MENU));
	}
}
