package player.units;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import scenedata.game.GameMap;

public class UnitsMng {

	protected GameMap map;
	protected WaypointMng waypoints;
	protected HashMap<Integer, Unit> register;
	
	public UnitsMng(GameMap map) {
		this.map = map;
		this.register = new HashMap<Integer, Unit>();
		this.waypoints = new WaypointMng(map);
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
	
	public Unit getUnit(int unitId){
		return register.get(unitId);
	}
	
	public boolean haveWay(int unitId){
		return waypoints.ways.containsKey(unitId);
	}
	
	public void addWay(int unitId, ArrayList<Point> way){
		waypoints.addWay(unitId, way);
	}
	
	public void removeWay(int unitId){
		waypoints.removeWay(unitId);
	}
	
	public void updUnit(String data) {
		
		String [] arr = data.split(":");
		int unitId = Integer.parseInt(arr[0]);
		
		Unit unit = getUnit(unitId);
		
		if(unit != null){
			int x = 0;
			int y = 0;
			boolean updPosition = false;
			
			switch(arr[1]){
				case "x": x = Integer.parseInt(arr[2]); updPosition = true; break;
				case "y": y = Integer.parseInt(arr[2]); updPosition = true; break;
				case "xy": x = Integer.parseInt(arr[2]); y = Integer.parseInt(arr[3]); updPosition = true; break;
			}
			
			if(updPosition){
				if(unit.x == x && unit.y == y){
					waypoints.removeWay(unitId);
				}
				else{
					this.map.map[unit.x][unit.y].removeUnit(unitId);
					this.map.map[x][y].addUnit(unit);
					unit.updateObj(arr);
				}
			}
		}
	}
}
