package builder;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;

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
					if(points.get(point).map[i][j]!=0)
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
		double[][]fractalMap=Fractals.Generate(sizeX, sizeY, rand.nextInt(5));
		for(int i=0;i<sizeX;i++){
			for(int t=0;t<sizeY;t++){
				map[i][t]=(byte) (fractalMap[i][t]<0.3?0:(fractalMap[i][t]-0.3)*15/0.7);
			}
		}
	}
}
class Fractals
{
    public static double GRoughness;
    public static double GBigSize;
    static final Random _rnd=new Random();
    static double[][] map;
    static boolean flag=false;
    public static double[][] Generate(int iWidth, int iHeight, double iRoughness)
    {
        map = new double[iWidth + 1][iHeight + 1];

        //Assign the four corners of the intial grid random color values
        //These will end up being the colors of the four corners		
        double c1 = _rnd.nextDouble()*0.3;
        double c2 = _rnd.nextDouble()*0.3;
        double c3 = _rnd.nextDouble()*0.3;
        double c4 = _rnd.nextDouble()*0.3;
        GRoughness = iRoughness;
        GBigSize = iWidth + iHeight;
        DivideGrid(map, 0, 0, iWidth, iHeight, c1, c2, c3, c4);
        return map;
    }
    public static double[][] Generate(int iWidth, int iHeight, double iRoughness, double[][] points){
    	if(points[0].length!=3)return null;
    	GRoughness = iRoughness;
        GBigSize = iWidth + iHeight;
    	map=new double[iWidth+1][iHeight+1];
    	for(int i=0;i<map.length;i++){
    		for(int t=0;t<map[i].length;t++){
    			map[i][t]=-1;
    		}
    	}
    	TreeSet<Integer> X=new TreeSet<>();
    	TreeSet<Integer> Y=new TreeSet<>();
    	X.add(0);
    	Y.add(0);
    	X.add(iWidth);
    	Y.add(iHeight);
    	for(int i=0;i<points.length;i++){
    		X.add((int)points[i][0]);
    		Y.add((int)points[i][1]);
    		map[(int) points[i][0]][(int) points[i][1]]=points[i][2];
    	}
    	for(int i0=0;i0<iWidth+1;i0+=iWidth){
			for(int i1=0;i1<iHeight+1;i1+=iHeight){
				if(map[i0][i1]==-1)map[i0][i1]=0;
			}
		}
    	Integer[]x=X.toArray(new Integer[0]);
    	Integer[]y=Y.toArray(new Integer[0]);
    	for(int i=0;i<x.length-1;i++){
    		for(int t=0;t<y.length-1;t++){
    			int x1=x[i];
    			int x2=x[i+1];
    			int y1=y[t];
    			int y2=y[t+1];
    			CheckMap(x1,y1,x2,y2);
    			double c1=map[x1][y1];
    			double c2=map[x2][y1];
    			double c3=map[x2][y2];
    			double c4=map[x1][y2];
    			DivideGrid(map, x1, y1, x2-x1+1, y2-y1+1, c1, c2, c3, c4);
    		}
    	}
    	for(double[]i:map){
    		for(double t:i){
    			t*=t;
    		}
    	}
    	return map;
    }
    protected static void CheckMap(int prevX, int prevY, int x, int y) {
    	
		if(map[prevX][prevY]==-1){
			if(prevX==0)
				map[0][prevY]=map[0][0]+(map[0][map[0].length-1]-map[0][0])*prevY/map[0].length;
			
			if(prevY==0)
				map[prevX][0]=map[0][0]+(map[map.length-1][0]-map[0][0])*prevX/map.length;
			
			if(prevX!=0&&prevY!=0)
				map[prevX][prevY]=(map[0][prevY]+map[prevX][0])/2;
		}
		
		if(map[x][y]==-1){
			if(x==map.length-1)
				map[map.length-1][y]=map[map.length-1][0]+(map[map.length-1][map[map.length-1].length-1]-map[map.length-1][0])*y/map[map.length-1].length;
			
			if(y==map[x].length-1)
				map[x][map[x].length-1]=map[0][map[x].length-1]+(map[map.length-1][map[x].length-1]-map[0][map[x].length-1])*x/map.length;
			
			if(x!=map.length-1&&y!=map[x].length-1)
				map[x][y]=(map[map.length-1][y]+map[x][map[x].length-1])/2;
		}
		
		if(map[prevX][y]==-1){
			if(prevX==0)
				map[0][y]=map[0][0]+(map[0][map[0].length-1]-map[0][0])*y/map[0].length;
			
			if(y==map[prevX].length-1)
				map[prevX][map[prevX].length-1]=map[0][map[prevX].length-1]+(map[map.length-1][map[prevX].length-1]-map[0][map[prevX].length-1])*prevX/map.length;
			
			if(prevX!=0&&y!=map[prevX].length-1)
				map[prevX][y]=(map[0][y]+map[prevX][map[prevX].length-1])/2;
		}
		
		if(map[x][prevY]==-1){
			if(x==map.length-1)
				map[map.length-1][prevY]=map[map.length-1][0]+(map[map.length-1][map[map.length-1].length-1]-map[map.length-1][0])*prevY/map[map.length-1].length;
            
            if(prevY==0)
				map[x][0]=map[0][0]+(map[map.length-1][0]-map[0][0])*x/map.length;
          	
            if(x!=map.length-1&&prevY!=0)
            	map[x][prevY]=(map[map.length-1][prevY]+map[x][0])/2;
		}
	}
	public static void DivideGrid(double[][] points, double x, double y, double width, double height, double c1, double c2, double c3, double c4)
    {
        int newWidth = (int) Math.floor(width / 2);
        int newHeight = (int) Math.floor(height / 2);

        if (width > 1 || height > 1)
        {
        	double middle;
        	if(!flag)
        		middle = ((c1 + c2 + c3 + c4) / 4) + Displace(newWidth + newHeight);
        	else{
        		middle=_rnd.nextDouble()+0.5;
        		flag=true;
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
            DivideGrid(points , x + newWidth, y, width - newWidth, newHeight, edge1, c2, edge2, middle);
            DivideGrid(points , x + newWidth, y + newHeight, width - newWidth, height - newHeight, middle, edge2, c3, edge3);
            DivideGrid(points , x, y + newHeight, newWidth, height - newHeight, edge4, middle, edge3, c4);
        }
        else	//This is the "base case," where each grid piece is less than the size of a pixel.
        {
            //The four corners of the grid piece will be averaged and drawn as a single pixel.
            double c = (c1 + c2 + c3 + c4) / 4;

            points[(int)(x)][(int)(y)] = c;
            if (width == 2)
            {
                points[(int)(x + 1)][(int)(y)] = c;
            }
            if (height == 2)
            {
                points[(int)(x)][(int)(y + 1)] = c;
            }
            if ((width == 2) && (height == 2))
            {
                points[(int)(x + 1)][(int)(y + 1)] = c;
            }
        }
    }
    private static double Rectify(double iNum)
    {
        if (iNum < 0)
        {
            iNum = 0;
        }
        else if (iNum > 1.0)
        {
            iNum = 1.0;
        }
        return iNum;
    }

    private static double Displace(double smallSize)
    {

        double max = smallSize / GBigSize * GRoughness;
        return (_rnd.nextDouble() - 0.5) * max;
    }
}
