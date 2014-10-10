package script.userapi;

import render.Render;
import misc.Environment;

public class api_CanvasResized extends ScriptApi {

	public static void execute() {
		Environment.updateFrameSize(Render.getWidth(), Render.getHeight());
	}
}
