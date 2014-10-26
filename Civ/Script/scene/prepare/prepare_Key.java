package scene.prepare;

import java.io.IOException;
import net.Message;
import net.Message.Prefix;
import network.Network;
import gui.GUI;
import gui.elements.GuiElementWindow;
import scenedata.game.GameData;
import script.Script;

public class prepare_Key extends Script {

	public static void released(GameData gamedata, GUI gui, String data) throws IOException {		
		String title = gui.getFocus();

		if(title != null && title.compareTo(scenegui_Prepare.uiWindiwNewTeam) == 0){
			// enter new team name
			GuiElementWindow window = (GuiElementWindow)gui.get(title);
			
			if(data.compareTo("Enter") == 0 && window.userText.length() > 0){
				gui.focusReset();
				window.setVisible(false);
				Network.sendMsg(new Message(Prefix.REQ_TEAM_CREATE, window.userText));
			}
			if(data.compareTo("Backspace") == 0 && window.userText.length() > 0){
				window.userText = window.userText.substring(0, window.userText.length()-1);
			}
			if(data.compareTo("Space") == 0){
				window.userText += " ";
			}
			if(data.compareTo("Escape") == 0){
				window.userText = "";
				window.setVisible(false);
				gui.focusReset();
			}
			else{
				if(data.length() == 1){
					char ch = data.charAt(0);
				
					if(Character.isLetter(ch)){
						window.userText += data;
					}
				}
			}
		}
	}

	public static void pressed(GameData gamedata, String data) {
		
	}
}
