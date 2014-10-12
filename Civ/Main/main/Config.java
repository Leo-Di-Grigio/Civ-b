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
	
	// network
	public static String serverAddress;
	public static int serverPort;
	
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
		
		// network
		serverAddress = "127.0.0.1";
		serverPort = 6600;
		
		Log.debug("Config load:\n\t1) renderMode: " + renderMode);
		Log.debug("\t2) " + frameWidth +"x" + frameHeight + ": fps " + fps);
	}
}
