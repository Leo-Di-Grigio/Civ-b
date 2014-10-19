package script.gui.button;

import gui.GUI;
import gui.elements.GuiElementWindow;

import java.io.IOException;

import main.Config;
import misc.Log;
import net.Message;
import net.Message.Prefix;
import network.Network;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_CreateTeam extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute gui_button_CreateTeam");
		
		GUI gui = (GUI)task.dataPost;
		
		GuiElementWindow window = (GuiElementWindow)gui.get("window_new_team");
		
		if(window != null){
			gui.focus("window_new_team");
			window.setVisible(true);
			
			Network.sendMsg(new Message(Prefix.REQ_TEAM_CREATE, Config.teamName));
		}
	}
}
