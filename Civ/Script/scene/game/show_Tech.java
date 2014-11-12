package scene.game;

import gui.GuiElement;

import java.io.IOException;

import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class show_Tech extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute show_Tech");
		
		GuiElement tech = task.sceneGui.get(scenegui_Game.uiTech);
		
		if(tech.getVisible()){
			tech.setVisible(false);
		}
		else{
			tech.setVisible(true);
		}
	}
}
