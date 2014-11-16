package main;

import misc.Enums;
import misc.Log;

public class Config {
	
	// os
	public static String os;
	public static String classPath;
	
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
	public static String playerName;
	public static String teamName;
	
	// gameplay settings
	public static boolean gameShowUnitPathPrevew = false;
	
	// chat
	public static int chatHistorySize = 1024;
	
	public Config() {
		// os
		os = System.getProperty("os.name").intern();
		Log.debug("OS: " + os);
		
		// classpath
		String tmp = System.getProperty("java.class.path");
		String [] arr = tmp.split(":");
		
		if(arr[0] != null){
			classPath = arr[0];
			
			if(classPath.endsWith(".jar")){
				if(os.startsWith("Linux")){
					classPath = "./";
				}
				
				if(os.startsWith("Windows")){
					classPath = "";
				}
			}
			else{
				if(os.startsWith("Linux")){
					classPath += "/";
				}
				
				if(os.startsWith("Windows")){
					classPath = "";
				}
			}
		}
		
		
		// render
		renderMode = Enums.RenderMode.OPENGL;
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
		playerName = "LDG";
		teamName = "Default Team";
		
		Log.debug("Config load:\n" +
				  "\t1) " + os + "\n" +
				  "\t2) " + classPath + "\n" + 
				  "\t3) renderMode: " + renderMode + "\n" +
				  "\t4) " + frameWidth +"x" + frameHeight + ": fps " + fps);
	}
}
