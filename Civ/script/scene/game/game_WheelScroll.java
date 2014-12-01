package scene.game;

import gui.GUI;
import gui.elements.GuiElementChat;

import java.awt.event.MouseWheelEvent;

import misc.Log;
import scenedata.game.GameData;
import script.gui.ScriptGui;

public class game_WheelScroll extends ScriptGui {
	
	public static void scroll(GUI gui, GameData gamedata, MouseWheelEvent event) {
		Log.debug("Execute game_WheelScroll");
		
		GuiElementChat chat = (GuiElementChat)gui.get(scenegui_Game.uiChat);
		
		if(chat != null){
			String elementTitle = gui.checkCollision();
			
			if(elementTitle != null){
				if(chat.getTitle().compareTo(elementTitle) == 0){
					int rotation = event.getWheelRotation();
				
					if(rotation < 0){
						chat.scrollDown();
					}
					else{
						if(rotation > 0){
							chat.scrollUp();
						}
					}
				}
			}
		}
	}
}
