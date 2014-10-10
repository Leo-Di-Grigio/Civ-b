package script.gui;

import gui.GUI;

public class gui_ElementSelect extends ScriptGui {

	public static void execute(GUI gui, String data) {
		if(data == null){
			gui.selectionReset();
		}
		else{
			gui.selectionSelect(data);
		}
	}
}
