package map;

import java.util.Set;
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
		Node [][] map = new Node[sizeX][sizeY];
		
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
	
	public Set<Integer> getUnits(int x, int y){
		return map[x][y].units;
	}
}