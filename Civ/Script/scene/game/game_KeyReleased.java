package scene.game;

import misc.Const;
import gui.GUI;
import gui.elements.GuiElementMinimap;
import recources.Recources;
import scenedata.game.GameData;
import script.gui.ScriptGui;

public class game_KeyReleased extends ScriptGui {

	public static void execute(GameData gamedata, GUI gui, String key){
		switch(key){
			case "H":
				gamedata.map.drawModeSwitch();
				changeMinimapTexture(gamedata, gui);
				break;
		}
	}
	
	public static void changeMinimapTexture(GameData gamedata, GUI gui){
		GuiElementMinimap map = (GuiElementMinimap)gui.get(scenegui_Game.uiMinimap);
		
		if(map != null){
			switch(gamedata.map.drawMode){	
				case TERRAIN:
					map.setMinimapTexture(Recources.getImage(Const.imgMinimap));
					break;
				
				case HEIGHT:
					map.setMinimapTexture(Recources.getImage(Const.imgMinimapHeight));
					break;
				
				case GEOLOGY:
					map.setMinimapTexture(Recources.getImage(Const.imgMinimapGeology));
					break;
					
				default:
					map.setMinimapTexture(Recources.getImage(Const.imgMinimap));
					break;
			}
		}
	}
}
