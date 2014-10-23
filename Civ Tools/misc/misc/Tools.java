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
	
	// for cycle map
	public static int getRangeX(int x1, int x2, int sizeX){
		if(x1 == x2){
			return 0;
		}
		else{
			if(x1 < x2){
				return Math.min(Math.abs(x2 - x1), ((sizeX - x2) + x1));
			}
			else{
				return Math.min(Math.abs(x2 - x1), ((sizeX - x1) + x2));
			}
		}
	}
	
	public static int getX(int x, int sizeX){
		if(x < 0){
			return sizeX + x;
		}
		else{
			if(x >= sizeX){
				return x % sizeX; 
			}
			else{
				return x;
			}
		}
	}
	
	public static int equals(int a, int b, int sizeX){
		if(a == b){
			return 0;
		}
		else{
			if(a < b){
				return -1;
			}
			else{
				int bx = (b + sizeX/2) % sizeX;
				
				if(a < bx){
					return 1;
				}
				else{
					return -1;
				}
			}
		}
	}
}
