package builder;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import misc.Tools;

public class GameMapGenerator {
	
	private static Random rand;
	
	public static byte [][] buildHeightMap(long seed, int sizeX, int sizeY){
		rand = new Random(seed);
		
		byte [][] nodes = new byte[sizeX][sizeY];
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				nodes[i][j] = 0;
			}
		}
		
		// islands
		int count = Tools.rand(20, 30, rand);
		HashMap<Point, Island> points = new HashMap<Point, Island>();
		
		for(int i = 0; i < count; ++i){
			points.put(new Point(
					Tools.rand(0, sizeX - 1, rand), 
					Tools.rand(0, sizeY - 1, rand)), 
					
					new Island(Tools.rand(7, 14, rand), 
							   Tools.rand(7, 14, rand),
							   rand)
			);
		}
		
		for(Point point: points.keySet()){
			int x = point.x;
			int y = point.y;
			
			for(int i = 0; i + x < sizeX && i < points.get(point).sizeX; ++i){
				for(int j = 0; j + y < sizeY && j < points.get(point).sizeY; ++j){
					nodes[i + x][j + y] = points.get(point).map[i][j];
				}
			}
		}
		
		return nodes;
	}
	
	public static void generateImageLog(byte [][] height, int sizeX, int sizeY, String fileName) {
		BufferedImage img = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		int rgb = 0;
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){

				if(height[i][j] == 0){
					rgb = 0x0000FF;
				}
				else{
					rgb = ((int)height[i][j]*16 << 16)  + ((int)height[i][j]*16 << 8) + (int)height[i][j]*16;
				}
			
				img.setRGB(i, j, rgb);
			}
		}
		
		File outputfile = new File(fileName);
	    
		try {
			ImageIO.write(img, "png", outputfile);
		} 
	    catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Island {
	
	int sizeX;
	int sizeY;
	byte [][] map;
	
	public Island(int sizeX, int sizeY, Random rand){
		map = new byte[sizeX][sizeY];
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		gen(rand);
	}
	
	public void gen(Random rand){
		for(int i = 0; i < sizeX; i++){
			for(int j = 0; j < sizeY; j++){
				map[i][j] = (byte)Tools.rand(1, 15, rand);
			}
		}
	}
}

