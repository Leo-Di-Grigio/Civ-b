package script.gui.pane;

import java.io.IOException;

import gui.GuiElement;
import gui.elements.GuiElementPane;
import misc.Enums;
import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_pane_MouseRelease extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException{
		Log.debug("Execute gui_pane_MouseRelease");
		
		GuiElementPane pane = (GuiElementPane)task.data;
		String key = pane.getCollisionElement();
		
		if(key != null){
			GuiElement element = pane.getElement(key);
			
			if(element.getScript() != null){
				element.getScript().execute(new Task(Enums.Task.MOUSE_CLICKED, element));
			}
		}
	}
}
