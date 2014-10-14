package script.gui.button;

import java.io.IOException;

import painter.Painter;
import misc.Enums;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_ChangeScene extends ScriptGui {

	private Enums.Scene toScene;
	
	public gui_button_ChangeScene(Enums.Scene scene) {
		this.toScene = scene;
	}
	
	@Override
	public void execute(Task e) throws IOException {
		Painter.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, this.toScene));
	}
}
