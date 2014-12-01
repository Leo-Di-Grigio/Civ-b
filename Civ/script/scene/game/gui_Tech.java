package scene.game;

import gui.GUI;
import gui.elements.GuiElementTechnologies;

import java.io.IOException;
import misc.Log;
import scenedata.game.GameData;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_Tech extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute gui_Tech");
	}

	public static void loadTech(GUI gui, GameData gamedata) {
		Log.debug("Execute gui_Tech.loadTech()");
		
		GuiElementTechnologies tech = (GuiElementTechnologies)gui.get(scenegui_Game.uiTech);
		
		if(tech != null){
			int teamId = gamedata.users.players.get(gamedata.clientId).teamId;
			tech.update(gamedata.users.teams.get(teamId).tech);
		}
	}
}
