package scene.prepare;

import painter.Painter;
import misc.Enums;
import misc.Log;
import script.Script;
import tasks.Task;

public class game_join extends Script {
	
	public static void execute(){
		Log.debug("Execute game_Join");
		
		Painter.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, Enums.Scene.GAME));
	}
}
