package player.units;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import scenedata.game.GameMap;

public class UnitsMng {

	protected GameMap map;
	protected static WaypointMng waypoints;
	protected static HashMap<Integer, Unit> register;
	
	public UnitsMng(GameMap map) {
		this.map = map;
		UnitsMng.register = new HashMap<Integer, Unit>();
		UnitsMng.waypoints = new WaypointMng(map);
	}
	
	public void addUnit(Unit unit){
		register.put(unit.id, unit);
		map.map[unit.x][unit.y].addUnit(unit);
	}
	
	public void delUnit(int unitId){
		Unit unit = register.remove(unitId);
		if(unit != null){
			map.map[unit.x][unit.y].removeUnit(unitId);
		}
	}
	
	public HashSet<Integer> getAll(int x, int y){
		return map.map[x][y].getAll();	
	}
	
	public static Unit getUnit(int unitId){
		return register.get(unitId);
	}
	
	public static boolean haveWay(int unitId){
		return waypoints.ways.containsKey(unitId);
	}
	
	public static void addWay(int unitId, ArrayList<Point> way){
		waypoints.addWay(unitId, way);
	}
	
	public static void removeWay(int unitId){
		waypoints.removeWay(unitId);
	}
	
	public void updUnit(String data) {
		String [] arr = data.split(":");
		int unitId = Integer.parseInt(arr[0]);
		Unit unit = getUnit(unitId);
		if(unit != null){
			unit.updateObj(arr);
		}
	}
}
