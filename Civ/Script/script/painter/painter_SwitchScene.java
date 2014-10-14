package script.painter;

import misc.Enums;
import misc.Log;
import painter.Painter;
import script.Script;

public class painter_SwitchScene extends Script {

	public static void execute(Enums.Scene scene) {
		Log.debug("SWITCH SCENE -> " + scene);
		Painter.switchScene(scene);
	}
}
