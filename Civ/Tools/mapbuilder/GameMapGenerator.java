package mapbuilder;

import java.awt.Point;
import java.util.HashMap;
import misc.Tools;
import scenedata.game.Node;

public class GameMapGenerator {
	
	public static Node [][] buildMap(int sizeX, int sizeY){
		Node [][] nodes = new Node[sizeX][sizeY];
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				nodes[i][j] = new Node();
			}
		}
		
		// islands
		int count = 5;
		HashMap<Point, Island> points = new HashMap<Point, Island>();
		
		for(int i = 0; i < count; ++i){
			points.put(new Point(Tools.rand(0, sizeX - 1), Tools.rand(0, sizeY - 1)), new Island(Tools.rand(10, 25), Tools.rand(10, 25)));
		}
		
		for(Point point: points.keySet()){
			int x = point.x;
			int y = point.y;
			
			for(int i = 0; i + x < sizeX && i < points.get(point).sizeX; ++i){
				for(int j = 0; j + y < sizeY && j < points.get(point).sizeY; ++j){
					nodes[i + x][j + y].height = points.get(point).map[i][j];
				}
			}
		}
		
		return nodes;
	}
}

class Island {
	
	int sizeX;
	int sizeY;
	byte [][] map;
	
	public Island(int sizeX, int sizeY){
		map = new byte[sizeX][sizeY];
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		gen();
	}
	
	public void gen(){
		for(int i = 0;i < sizeX; i++){
			for(int t = 0; t < sizeY; t++){
				map[i][t] = (byte) Tools.rand(1, 15);
			}
		}
	}
}