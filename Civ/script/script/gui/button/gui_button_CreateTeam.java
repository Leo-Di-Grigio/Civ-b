package script.gui.button;

import gui.GUI;
import gui.elements.GuiElementWindow;

import java.io.IOException;

import misc.Log;
import scene.prepare.scenegui_Prepare;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_CreateTeam extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute gui_button_CreateTeam");
		
		GUI gui = (GUI)task.sceneGui;
		
		GuiElementWindow window = (GuiElementWindow)gui.get(scenegui_Prepare.uiWindiwNewTeam);
		
		if(window != null){
			gui.focus(scenegui_Prepare.uiWindiwNewTeam);
			window.setVisible(true);
		}
	}
}
