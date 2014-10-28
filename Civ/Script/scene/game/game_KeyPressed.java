package scene.game;

import gui.GUI;
import gui.elements.GuiElementWindow;

import java.awt.event.KeyEvent;

import misc.Enums;
import misc.Environment;
import misc.ToolsKeyUpdate;
import scenedata.game.GameData;
import script.gui.ScriptGui;

public class game_KeyPressed extends ScriptGui {
	
	public static void execute(GameData gamedata, GUI gui, KeyEvent event){
		
		GuiElementWindow chatEnter = (GuiElementWindow)gui.get(scenegui_Game.uiChatEntry);
		
		if(chatEnter != null){
			if(chatEnter.getVisible()){
				chatEnter(chatEnter, event);
			}
			else{
				normalEnter(gamedata, gui, event);
			}
		}
		else{
			normalEnter(gamedata, gui, event);
		}
	}
	
	private static void chatEnter(GuiElementWindow chatEnter, KeyEvent event) {
		String key = KeyEvent.getKeyText(event.getKeyCode());
		
	
		if(key.compareTo("Escape") == 0){
			chatEnter.userText = "";
			chatEnter.setVisible(false);
		}
		else{
			chatEnter.userText = ToolsKeyUpdate.update(chatEnter.userText, key, event, 128);
		}
	}
	
	private static void normalEnter(GameData gamedata, GUI gui, KeyEvent event){
		switch(KeyEvent.getKeyText(event.getKeyCode())){
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
