package script.gui;

import painter.Painter;
import gui.GUI;
import gui.GuiElement;

public class gui_ElementSelect extends ScriptGui {

	public static void execute(GUI gui, String data) {
		if(data == null){
			gui.selectionReset();
			Painter.setTooltip(null);
		}
		else{
			GuiElement element = gui.selectionSelect(data);
			
			if(element != null){
				Painter.setTooltip(element.getTooltip());
			}
		}
	}
}
