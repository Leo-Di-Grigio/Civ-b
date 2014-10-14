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
		int count = Tools.rand(20, 50, rand);
		HashMap<Point, Island> points = new HashMap<Point, Island>();
		
		for(int i = 0; i < count; ++i){
			points.put(new Point(
					Tools.rand(0, sizeX - 1, rand), 
					Tools.rand(0, sizeY - 1, rand)), 
					
					new Island(Tools.rand((int)(sizeX*0.1), (int)(sizeX*0.3), rand), 
							Tools.rand((int)(sizeY*0.1), (int)(sizeY*0.3), rand),
							   rand)
			);
		}
		
		for(Point point: points.keySet()){
			int x = point.x;
			int y = point.y;
			
			for(int i = 0; i + x < sizeX && i < points.get(point).sizeX; ++i){
				for(int j = 0; j + y < sizeY && j < points.get(point).sizeY; ++j){
					if(points.get(point).map[i][j] != 0){
						nodes[i + x][j + y] = points.get(point).map[i][j];
					}
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
		double[][] fractalMap = Fractals.Generate(sizeX, sizeY, rand.nextInt(5), rand);
		
		for(int i = 0; i < sizeX; i++){
			for(int t = 0; t < sizeY; t++){
				if(fractalMap[i][t] < 0.3){
					map[i][t] = 0;
				}
				else{
					map[i][t] = (byte)((fractalMap[i][t] - 0.3) * 15 / 0.7);
				}
			}
		}
	}
}

class Fractals {
	
    private static double roughness;
    private static double bigSize;
    private static Random random;
    private static double [][] map;
    private static boolean flag = false;
    
    public static double [][] Generate(int sizeX, int sizeY, double roughness, Random rand) {
    	Fractals.random = rand;
        map = new double[sizeX + 1][sizeY + 1];

        //Assign the four corners of the intial grid random color values
        //These will end up being the colors of the four corners		
        double c1 = random.nextDouble() * 0.3;
        double c2 = random.nextDouble() * 0.3;
        double c3 = random.nextDouble() * 0.3;
        double c4 = random.nextDouble() * 0.3;
        
        Fractals.roughness = roughness;
        Fractals.bigSize = sizeX + sizeY;
        
        DivideGrid(map, 0, 0, sizeX, sizeY, c1, c2, c3, c4);
        
        return map;
    }
    
	public static void DivideGrid(double [][] points, double x, double y, double sizeX, double sizeY, double c1, double c2, double c3, double c4) {
        
		int newWidth  = (int) Math.floor(sizeX / 2);
        int newHeight = (int) Math.floor(sizeY / 2);

        if (sizeX > 1 || sizeY > 1) {
        	double middle;
        	
        	if(!flag){
        		middle = ((c1 + c2 + c3 + c4) / 4) + Displace(newWidth + newHeight);
        	}
        	else{
        		middle = random.nextDouble() + 0.5;
        		flag = true;
        	}
        	
            double edge1 = ((c1 + c2) / 2);
            double edge2 = ((c2 + c3) / 2);
            double edge3 = ((c3 + c4) / 2);
            double edge4 = ((c4 + c1) / 2);
            
            //Make sure that the midpoint doesn't accidentally "randomly displaced" past the boundaries!
            middle = Rectify(middle);
            edge1 = Rectify(edge1);
            edge2 = Rectify(edge2);
            edge3 = Rectify(edge3);
            edge4 = Rectify(edge4);
            
            //Do the operation over again for each of the four new grids.			
            DivideGrid(points, x, y, newWidth, newHeight, c1, edge1, middle, edge4);
            DivideGrid(points, x + newWidth, y, sizeX - newWidth, newHeight, edge1, c2, edge2, middle);
            DivideGrid(points, x + newWidth, y + newHeight, sizeX - newWidth, sizeY - newHeight, middle, edge2, c3, edge3);
            DivideGrid(points, x, y + newHeight, newWidth, sizeY - newHeight, edge4, middle, edge3, c4);
        }
        else {
        	//This is the "base case," where each grid piece is less than the size of a pixel.
            //The four corners of the grid piece will be averaged and drawn as a single pixel.
            double c = (c1 + c2 + c3 + c4) / 4;

            points[(int)(x)][(int)(y)] = c;
            if (sizeX == 2) {
                points[(int)(x + 1)][(int)(y)] = c;
            }
            if (sizeY == 2) {
                points[(int)(x)][(int)(y + 1)] = c;
            }
            if ((sizeX == 2) && (sizeY == 2)) {
                points[(int)(x + 1)][(int)(y + 1)] = c;
            }
        }
    }
    private static double Rectify(double num){
        if (num < 0){
            num = 0;
        }
        else if (num > 1.0){
            num = 1.0;
        }
        
        return num;
    }

    private static double Displace(double smallSize){
        double max = smallSize / bigSize * roughness;
        return (random.nextDouble() - 0.5) * max;
    }
}
