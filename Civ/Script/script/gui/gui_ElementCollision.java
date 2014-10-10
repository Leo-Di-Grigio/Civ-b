package script.gui;

import misc.Enums;
import painter.Painter;
import gui.GUI;
import tasks.Task;

public class gui_ElementCollision extends ScriptGui {
	
	public static void execute(GUI gui) {
		String title = gui.checkCollision();
		
		if(title == null){
			Painter.currentScene.addTask(new Task(Enums.Task.GUI_SELECTION_RESET, null));
		}
		else{
			Painter.currentScene.addTask(new Task(Enums.Task.GUI_SELECTION_SELECT, title));
		}
	}
}
