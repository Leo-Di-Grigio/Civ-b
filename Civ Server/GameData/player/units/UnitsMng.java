package player.units;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import map.GameMap;
import misc.Log;

public class UnitsMng {
	
	private HashMap<Integer, Unit> units;
	private HashMap<Integer, Set<Integer>> playerUnit; //<playerId, Set<unitId> >
	private GameMap map;
	
	public UnitsMng(GameMap map){
		this.units = new HashMap<Integer, Unit>();
		this.playerUnit = new HashMap<Integer, Set<Integer>>();
		this.map = map;
	}
	
	public void registerPlayer(int playerId){
		playerUnit.put(playerId, new HashSet<Integer>());
	}
	
	public void removePlayer(int playerId){
		playerUnit.remove(playerId);
	}
	
	public void addUnit(Unit unit){
		units.put(unit.id, unit);
		playerUnit.get(unit.playerId).add(unit.id);
		map.addUnit(unit.x, unit.y, unit.id);
		
		Log.debug("add Unit Id: " + unit.id + " type: " + unit.type + " x: " + unit.x + " y: " + unit.y + " playerId: " + unit.playerId);
	}
	
	public void removeUnit(int unitId){
		Unit unit = units.remove(unitId);
		
		if(unit != null){
			map.removeUnit(unit.x, unit.y, unit.id);
			playerUnit.get(unit.playerId).remove(unit.id);
		}
	}
	
	public Unit getUnit(int unitId){
		return units.get(unitId);
	}
	
	public Set<Integer> getUnits(int x, int y){
		return map.getUnits(x, y);
	}
	
	public Set<Integer> getPlayersUnits(int playerId){
		return playerUnit.get(playerId);
	}
	
	public void spawnAllPlayersAvatars(HashSet<Unit> avatars){
		for(Unit unit: avatars){
			addUnit(unit); 
		}
	}
}
