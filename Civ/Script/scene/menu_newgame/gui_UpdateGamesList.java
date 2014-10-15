package scene.menu_newgame;

import gui.GUI;
import gui.elements.GuiElementTable;
import misc.Log;
import script.gui.ScriptGui;

public class gui_UpdateGamesList extends ScriptGui {

	public static void execute(GUI gui, String data) {
		Log.debug("Execute gui_UpdateGamesList");
		
		if(gui != null){
			GuiElementTable gameslist = (GuiElementTable)gui.get("gameslist");
			
			if(gameslist != null){
				gameslist.updateList(data);
			}
		}
	}
}
