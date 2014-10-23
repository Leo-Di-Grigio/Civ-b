package player.units;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import scenedata.game.GameMap;

public class WaypointMng {
	
	protected HashMap<Integer, ArrayList<Point>> ways; // map<unitId, pointsList<WP>>;
	protected GameMap map;
	
	public WaypointMng(GameMap map){
		this.map = map;
		this.ways = new HashMap<Integer, ArrayList<Point>>();
	}
	
	public void addWay(int unitId, ArrayList<Point> way){
		if(ways.containsKey(unitId)){
			removeWay(unitId);
		}
		
		for(Point point: way){
			map.map[point.x][point.y].addWaypoint(unitId);
		}
		ways.put(unitId, way);
	}
	
	public void removeWay(int unitId){
		if(ways.containsKey(unitId)){
			ArrayList<Point> way = ways.remove(unitId);
			
			for(Point point: way){
				map.map[point.x][point.y].removeWaypoint(unitId);
			}
		}
	}
}
