package misc;

import main.Config;

public class Log {
	
	public static void msg(String msg){
		System.out.println(msg);
	}
	
	public static void err(String msg){
		System.err.println("ERROR: " + msg);
	}
	
	public static void debug(String msg){
		if(Config.debug){
			System.out.println("DEBUG: " + msg);
		}
	}
	
	public static void showUsedMemory(){ // kb
		Log.msg("" + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024));
	}
}
