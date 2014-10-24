package scene.game;

import java.io.IOException;

import net.Message;
import net.Message.Prefix;
import network.Network;
import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class game_EndTurn extends ScriptGui {

	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute game_EndTurn");
		Network.sendMsg(new Message(Prefix.GAME_TURN_END, null));
	}
}
