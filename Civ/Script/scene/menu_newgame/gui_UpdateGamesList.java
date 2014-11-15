package scene.menu_newgame;

import gui.GUI;
import gui.elements.GuiElementTable;
import misc.Log;
import scene.choosegame.scenegui_ChooseGame;
import script.gui.ScriptGui;

public class gui_UpdateGamesList extends ScriptGui {

	public static void execute(GUI gui, String data) {
		Log.debug("Execute gui_UpdateGamesList");
		
		if(gui != null){
			GuiElementTable gameslist = (GuiElementTable)gui.get(scenegui_ChooseGame.uiGameList);
			
			if(gameslist != null){
				gameslist.updateList(data);
			}
		}
	}
}
