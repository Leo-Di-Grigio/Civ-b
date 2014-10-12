package misc;

public class Log {
	
	public static void msg(String msg){
		System.out.println(msg);
	}
	
	public static void err(String msg){
		System.err.println("ERROR: " + msg);
	}
	
	public static void debug(String msg){
		if(Const.debug){
			System.err.println("DEBUG: " + msg);
		}
	}
	
	public static void service(String msg){
		if(Const.service){
			System.out.println("SERVICE: " + msg);
		}
	}
}
