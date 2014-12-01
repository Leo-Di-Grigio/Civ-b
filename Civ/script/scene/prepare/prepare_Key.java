package scene.prepare;

import java.awt.event.KeyEvent;
import java.io.IOException;

import misc.ToolsKeyUpdate;
import net.Message;
import net.Message.Prefix;
import network.Network;
import gui.GUI;
import gui.elements.GuiElementWindow;
import scenedata.game.GameData;
import script.Script;

public class prepare_Key extends Script {

	public static void released(GameData gamedata, GUI gui, KeyEvent event) throws IOException {		
		String title = gui.getFocus();

		if(title != null && title.compareTo(scenegui_Prepare.uiWindiwNewTeam) == 0){
			// enter new team name
			GuiElementWindow window = (GuiElementWindow)gui.get(title);
			String key = KeyEvent.getKeyText(event.getKeyCode());
			
			if(key.compareTo("Enter") == 0 && window.userText.length() > 0){
				gui.focusReset();
				window.setVisible(false);
				Network.sendMsg(new Message(Prefix.REQ_TEAM_CREATE, window.userText));
			}
			else{
				if(key.compareTo("Escape") == 0){
					window.userText = "";
					window.setVisible(false);
					gui.focusReset();
				}
				else{
					window.userText = ToolsKeyUpdate.update(window.userText, key, event, 36);
				}
			}
		}
	}

	public static void pressed(GameData gamedata, KeyEvent data) {
		
	}
}
