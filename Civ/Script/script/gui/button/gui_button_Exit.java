package script.gui.button;

import misc.Log;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_Exit extends ScriptGui {

	@Override
	public void execute(Task task){
		Log.debug("Execute gui_button_Exit");
		System.gc();
		System.exit(0);
	}
}
