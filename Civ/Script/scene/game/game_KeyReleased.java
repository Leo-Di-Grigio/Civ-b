package scene.game;

import scenedata.game.GameData;
import script.gui.ScriptGui;

public class game_KeyReleased extends ScriptGui {

	public static void execute(GameData gamedata, String key){
		switch(key){
			case "H":
				gamedata.map.drawModeSwitch();
				break;
		}
	}
}
