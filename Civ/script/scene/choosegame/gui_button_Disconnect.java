package scene.choosegame;

import java.io.IOException;

import network.Network;
import painter.Painter;
import misc.Enums;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_Disconnect extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Network.disconnect();
		Painter.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, Enums.Scene.MENU));
	}
}
