package scene.game;

import misc.Enums;
import misc.Environment;
import scenedata.game.GameData;
import script.gui.ScriptGui;

public class game_KeyPressed extends ScriptGui {
	
	public static void execute(GameData gamedata, String key){
		switch(key){
			case "W":
				Environment.moveCamera(Enums.Direct.UP);
				break;
				
			case "S":
				Environment.moveCamera(Enums.Direct.DOWN);
				break;
				
			case "A":
				Environment.moveCamera(Enums.Direct.LEFT);
				break;
				
			case "D":
				Environment.moveCamera(Enums.Direct.RIGHT);
				break;
		}
	}
}
