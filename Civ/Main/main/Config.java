package main;

import misc.Enums;
import misc.Log;

public class Config {
	
	// render
	public static Enums.RenderMode renderMode;
	public static int fps;
	public static int frameRate;
	
	// window
	public static int frameWidth;
	public static int frameHeight;
	
	// debug
	public static boolean debug;
	
	public Config(){
		// render
		renderMode = Enums.RenderMode.NATIVE;
		fps = 60;
		frameRate = 1000/fps;
		
		// window
		frameWidth = 1024;
		frameHeight = 768;
		
		// debug
		debug = true;
		
		Log.msg("Config load:\n\t1) renderMode: " + renderMode);
		Log.msg("\t2) " + frameWidth +"x" + frameHeight + ": fps " + fps);
	}
}
