package script.gui.button;

import gui.GUI;
import gui.elements.GuiElementWindow;
import java.io.IOException;
import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_CreateTeam extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute gui_button_CreateTeam");
		
		GUI gui = (GUI)task.dataPost;
		
		GuiElementWindow window = (GuiElementWindow)gui.get("window_new_team");
		
		if(window != null){
			gui.focus("window_new_team");
			window.setVisible(true);
		}
	}
}
