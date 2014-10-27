package scene.game;

import java.io.IOException;

import net.Message;
import net.Message.Prefix;
import network.Network;
import painter.Painter;
import misc.Enums;
import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class game_Exit extends ScriptGui {

	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute game_Exit");
		
		Painter.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, Enums.Scene.CHOOSE_GAME));
		Network.sendMsg(new Message(Prefix.REQ_GAME_LEAVE, null));
	}
}
