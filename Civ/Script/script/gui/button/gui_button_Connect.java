package script.gui.button;

import java.io.IOException;

import network.Network;
import main.Config;
import misc.Enums;
import misc.Log;
import painter.Painter;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_Connect extends ScriptGui {

	@Override
	public void execute(Task e) throws IOException {
		Log.debug("Execute gui_button_Connect");
		
		Network.createConnection(Config.serverAddress, Config.serverPort);
		
		Painter.currentScene.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, "menu_newGame"));
	}
}
