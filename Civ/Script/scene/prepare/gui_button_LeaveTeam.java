package scene.prepare;

import java.io.IOException;

import net.Message;
import net.Message.Prefix;
import network.Network;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_LeaveTeam extends ScriptGui {
	
	
	@Override
	public void execute(Task task) throws IOException {
		Network.sendMsg(new Message(Prefix.REQ_TEAM_CHOOSE, ""+(-1)));
	}
}
