package script.gui.multitable;

import gui.elements.GuiElementMultiTable;

import java.io.IOException;

import misc.Environment;
import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_multitable_Select extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("gui_multitable_Select");
		
		GuiElementMultiTable list = (GuiElementMultiTable)task.data;
		
		if(list != null){
			int x = Environment.mouseX;
			int y = Environment.mouseY;
			
			if(y > list.getDrawY() && y < list.getDrawY() + list.getSizeY() - 10){
				if(x > list.getDrawX() + 10 && x < list.getDrawX() + list.getSizeX() - 32){
					int line = (y - (list.getDrawY() + 5)) / GuiElementMultiTable.lineSize;
					list.select(line);
				}
				else{
					float size = list.getSizeY();
					float pos = list.getDrawY();
					float percent = (y - pos)/(size - 10.0f);
					list.scroll(percent, y);
				}
			}
		}
	}
}
