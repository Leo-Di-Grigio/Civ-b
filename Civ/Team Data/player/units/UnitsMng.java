package player.units;

import java.util.HashMap;
import java.util.HashSet;

import scenedata.game.GameMap;

public class UnitsMng {

	protected GameMap map;
	protected static HashMap<Integer, Unit> register;
	
	public UnitsMng(GameMap map) {
		this.map = map;
		register = new HashMap<Integer, Unit>();
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

	public void updUnit(String data) {
		String [] arr = data.split(":");
		int unitId = Integer.parseInt(arr[0]);
		Unit unit = getUnit(unitId);
		if(unit != null){
			unit.updateObj(arr);
		}
	}
}
