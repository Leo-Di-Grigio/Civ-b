package script.gui;

import gui.GUI;

public class gui_CurosorHide extends ScriptGui {

	public static void execute(GUI gui) {
		gui.cursorShow(false);
	}
}
