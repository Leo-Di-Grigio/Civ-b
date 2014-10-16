package script.gui.button;

import java.io.IOException;

import main.Config;
import net.Message;
import net.Message.Prefix;
import network.Network;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_CreateTeam extends ScriptGui {
	
	@Override
	public void execute(Task e) throws IOException {
		Network.sendMsg(new Message(Prefix.REQ_TEAM_CREATE, Config.teamName));
	}
}
