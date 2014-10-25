package player.units;

import game.GameBroadcasting;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import net.Message;
import net.Message.Prefix;
import map.GameMap;

public class UnitsMng {
	
	private HashMap<Integer, Unit> units;
	private HashMap<Integer, HashSet<Integer>> playerUnit; //<playerId, Set<unitId> >
	private GameMap map;
	
	public UnitsMng(GameMap map){
		this.units = new HashMap<Integer, Unit>();
		this.playerUnit = new HashMap<Integer, HashSet<Integer>>();
		this.map = map;
	}
	
	public void registerPlayer(int playerId){
		playerUnit.put(playerId, new HashSet<Integer>());
	}
	
	public void removePlayer(int playerId){
		playerUnit.remove(playerId);
	}
	
	public void addUnit(Unit unit, GameBroadcasting broad) throws IOException{
		units.put(unit.id, unit);
		playerUnit.get(unit.playerId).add(unit.id);
		map.addUnit(unit.x, unit.y, unit.id);
		
		broad.sendToPlayers(unit.toMessage());
	}
	
	public void addUnits(HashSet<Unit> unitsSet, GameBroadcasting broad) throws IOException{
		for(Unit unit: unitsSet){
			addUnit(unit, broad); 
		}
	}
	
	public void removeUnit(int unitId, GameBroadcasting broad) throws IOException{
		Unit unit = units.remove(unitId);
		
		if(unit != null){
			map.removeUnit(unit.x, unit.y, unit.id);
			playerUnit.get(unit.playerId).remove(unit.id);
			
			broad.sendToPlayers(new Message(Prefix.DEL_UNIT, "" + unit.id));
		}
	}
	
	public Unit getUnit(int unitId){
		return units.get(unitId);
	}
	
	public HashSet<Integer> getUnits(int x, int y){
		return map.getUnits(x, y);
	}
	
	public HashSet<Integer> getPlayersUnits(int playerId){
		return playerUnit.get(playerId);
	}
}
