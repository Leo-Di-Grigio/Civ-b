package scene.prepare;

import java.io.IOException;

import net.Message;
import net.Message.Prefix;
import network.Network;
import painter.Painter;
import misc.Enums;
import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_LeaveGame extends ScriptGui {
	
	@Override
	public void execute(Task e) throws IOException {
		Log.debug("Execute gui_button_LeaveGame");
		Network.sendMsg(new Message(Prefix.REQ_GAME_LEAVE, null));
		Painter.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, Enums.Scene.CHOOSE_GAME));
	}
}
