package scene.game;

import java.awt.event.KeyEvent;
import java.io.IOException;

import net.Message;
import net.Message.Prefix;
import network.Network;
import misc.Const;
import gui.GUI;
import gui.elements.GuiElementChat;
import gui.elements.GuiElementMinimap;
import gui.elements.GuiElementWindow;
import scenedata.game.GameData;
import script.gui.ScriptGui;

public class game_KeyReleased extends ScriptGui {

	public static void execute(GameData gamedata, GUI gui, KeyEvent event) throws IOException{
		GuiElementWindow chatEnter = (GuiElementWindow)gui.get(scenegui_Game.uiChatEntry);
		String key = KeyEvent.getKeyText(event.getKeyCode());
		
		if(key.compareTo("Enter") == 0){
			useChat(gui);
			return;
		}
		
		if(!chatEnter.getVisible()){
			switch(key){
				case "H":
					gamedata.map.drawModeSwitch();
					changeMinimapTexture(gamedata, gui);
					break;
			}
		}
	}
	
	private static void useChat(GUI gui) throws IOException {
		GuiElementWindow chatEnter = (GuiElementWindow)gui.get(scenegui_Game.uiChatEntry);
		GuiElementChat chat = (GuiElementChat)gui.get(scenegui_Game.uiChat);
		
		if(chatEnter != null && chat != null){
			if(chatEnter.getVisible()){
				if(chatEnter.userText.compareTo("") != 0){
					Network.sendMsg(new Message(Prefix.CHAT_MSG, chatEnter.userText));
				}
				
				chatEnter.userText = "";
				chatEnter.setVisible(false);
			}
			else{
				chatEnter.setVisible(true);
			}
		}
	}

	private static void changeMinimapTexture(GameData gamedata, GUI gui){
		GuiElementMinimap map = (GuiElementMinimap)gui.get(scenegui_Game.uiMinimap);
		
		if(map != null){
			switch(gamedata.map.drawMode){	
				case TERRAIN:
					map.setMinimapTexture(Const.imgMinimap);
					break;
				
				case HEIGHT:
					map.setMinimapTexture(Const.imgMinimapHeight);
					break;
				
				case GEOLOGY:
					map.setMinimapTexture(Const.imgMinimapGeology);
					break;
					
				case TERMAL:
					map.setMinimapTexture(Const.imgMinimapTemperature);
					break;
					
				default:
					map.setMinimapTexture(Const.imgMinimap);
					break;
			}
		}
	}
}
