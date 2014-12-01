package scene.prepare;

import gui.GUI;
import gui.GuiElement;

import java.io.IOException;

import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_window_Click extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute gui_window_Click");
		
		GUI gui = (GUI)task.sceneGui;
		GuiElement element = (GuiElement)task.data;
		
		element.setVisible(false);
		gui.focusReset();
	}
}
