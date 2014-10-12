package scene.menu_newgame;

import gui.GUI;
import gui.elements.GuiElementGamesList;
import misc.Log;
import script.gui.ScriptGui;

public class gui_GamesList extends ScriptGui {

	public static void execute(GUI gui, String data) {
		Log.err("Execute gui_GamesList");
		
		if(gui != null){
			GuiElementGamesList gameslist = (GuiElementGamesList)gui.get("gameslist");
			
			if(gameslist != null){
				gameslist.updateList(data);
			}
		}
	}
}
