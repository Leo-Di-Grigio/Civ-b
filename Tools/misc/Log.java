package misc;

import main.Config;

public class Log {
	
	public static void msg(String msg){
		System.out.println(msg);
	}
	
	public static void err(String msg){
		System.err.println(msg);
	}
	
	public static void debug(String msg){
		if(Config.debug){
			System.err.println("DEBUG: " + msg);
		}
	}
}
