package misc;

import java.util.Random;

public class Tools {
	
	protected static Random randomizer;
	
	public Tools(){
		randomizer = new Random(System.currentTimeMillis());
	}
	
	public static int rand(int min, int max, Random rand){
		return rand.nextInt((max - min) + 1) + min;
	}
	
	public static int rand(int min, int max){
		return randomizer.nextInt((max - min) + 1) + min;
	}
}
