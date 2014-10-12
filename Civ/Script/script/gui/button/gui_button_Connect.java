package script.gui.button;

import java.io.IOException;
import misc.Enums;
import misc.Log;
import painter.Painter;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_Connect extends ScriptGui {

	@Override
	public void execute(Task e) throws IOException {
		Log.debug("Execute gui_button_Connect");
		
		Painter.currentScene.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, Enums.Scene.MENU_NEWGAME));
	}
}
