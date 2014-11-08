package scene.game;

import gui.elements.GuiElementUnits;

import java.io.IOException;

import painter.Painter;
import player.units.Unit;
import misc.Enums;
import misc.Environment;
import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_unit_Select extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute gui_unit_Select");
		
		GuiElementUnits units = (GuiElementUnits)task.data;
		
		if(units != null){
			int x = Environment.mouseX;
			int y = Environment.mouseY;
			
			if(x > units.getDrawX() + 5 && x < units.getDrawX() + units.getSizeX() - 5 &&
			   y > units.getDrawY() + 5 && y < units.getDrawY() + units.getSizeY() - 5)
			{
				int line = (y - units.getDrawY() + 5)/36;
				int row = (x - units.getDrawX() + 5)/36;
				units.select(line, row);
				
				Unit unit = units.getSelectedUnit();
				Painter.addTask(new Task(Enums.Task.GUI_UNIT_SELECT, unit));
			}
		}
	}
}
