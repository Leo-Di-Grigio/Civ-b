package scene.game;

import misc.Tools;
import gui.GUI;
import gui.elements.GuiElementChat;
import script.gui.ScriptGui;

public class game_Chat extends ScriptGui {

	public static void msg(GUI gui, String data) {
		GuiElementChat chat = (GuiElementChat)gui.get(scenegui_Game.uiChat);
		
		if(chat != null){
			chat.addLine("[" + Tools.getTime(System.currentTimeMillis()) + "]"+ data);
		}
	}
}
