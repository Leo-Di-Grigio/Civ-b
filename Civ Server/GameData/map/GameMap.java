package map;

import java.awt.Point;
import java.util.HashSet;

import misc.Tools;
import builder.GameMapGenerator;

public class GameMap {
	
	// params
	public long seed;
	public int sizeX = 0;
	public int sizeY = 0;
	public int tMin = 0;
	public int tMax = 0;
	
	// layers
	private Node [][] map;
	public  byte [][] height;
	public  byte [][] geology;
	public  byte [][] termal;
	
	public GameMap(long seed, int sizeX, int sizeY, int tMin, int tMax) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.seed = seed;
		this.tMin = tMin;
		this.tMax = tMax;
		
		buildMap();
	}
	
	private void buildMap(){
		this.map = new Node[sizeX][sizeY];
		
		height  = GameMapGenerator.buildHeightMap(seed, sizeX, sizeY);
		geology = GameMapGenerator.buildGeologyMap(seed, sizeX, sizeY);
		termal  = GameMapGenerator.buildTermalMap(seed, sizeX, sizeY, tMin, tMax);
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				map[i][j] = new Node();
				map[i][j].height = height[i][j];
				map[i][j].geology = geology[i][j];
				map[i][j].termal = termal[i][j];
			}
		}
		
		System.gc();
	}
	
	public void addObject(int x, int y, int objectId){
		map[x][y].gameObjects.add(objectId);
	}
	
	public void removeObject(int x, int y, int objectId){
		map[x][y].gameObjects.remove(objectId);
	}
	
	public HashSet<Integer> getObjects(int x, int y){
		return map[x][y].gameObjects;
	}
	
	public HashSet<Point> getSpawnPoints(int teamsCount){
		HashSet<Point> points = new HashSet<Point>();
		
		for(int i = 0; i < teamsCount; ++i){
			int height = 0;
			int x = 0;
			int y = 0;
			
			do{
				x = Tools.rand(0, sizeX - 1);
				y = Tools.rand(0, sizeY - 1);
				height = map[x][y].height;
			}
			while(height == 0);
			
			points.add(new Point(x, y));
		}
		
		return points;
	}
}