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
	
	// layers
	private Node [][] map;
	
	public GameMap(long seed, int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.seed = seed;
		
		buildMap();
	}
	
	private void buildMap(){
		this.map = new Node[sizeX][sizeY];
		
		byte [][] height  = GameMapGenerator.buildHeightMap(seed, sizeX, sizeY);
		byte [][] geology = GameMapGenerator.buildGeologyMap(seed, sizeX, sizeY);
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				map[i][j] = new Node();
				map[i][j].height = height[i][j];
				map[i][j].geology = geology[i][j];
			}
		}
		
		System.gc();
	}
	
	public void addUnit(int x, int y, int unitId){
		map[x][y].units.add(unitId);
	}
	
	public void removeUnit(int x, int y, int unitId){
		map[x][y].units.remove(unitId);
	}
	
	public HashSet<Integer> getUnits(int x, int y){
		return map[x][y].units;
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