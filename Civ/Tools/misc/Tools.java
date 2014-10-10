package misc;

import java.util.Random;

public class Tools {
	
	protected static Random rand;
	
	public Tools(){
		rand = new Random(System.currentTimeMillis());
	}
	
	public static int rand(int min, int max){
		return rand.nextInt((max - min) + 1) + min;
	}
	
	public static void showUsedMemory(){ // kb
		Log.msg("" + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024));
	}
}
