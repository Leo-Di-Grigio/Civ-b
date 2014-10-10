package scene.painter;

import misc.Enums;
import misc.Log;
import painter.Painter;
import script.Script;

public class painter_SwitchScene extends Script {

	public static void execute(String key) {
		Log.debug("SWITCH SCENE -> " + key);
		Painter.switchScene(Enums.Scene.getScene(key));
	}
}
