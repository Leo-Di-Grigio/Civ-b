package player.units;

import java.util.HashMap;
import java.util.Vector;

import misc.Log;
import scenedata.game.GameMap;

public class UnitsMng {

	protected GameMap map;
	protected HashMap<Integer, Unit> register;
	
	public UnitsMng(GameMap map) {
		this.map = map;
		this.register = new HashMap<Integer, Unit>();
	}
	
	public void add(Unit unit){
		if(unit != null){
			map.nodes[unit.x][unit.y].add(unit);
			register.put(unit.id, unit);
		}
	}
	
	public void remove(int id){
		if(register.containsKey(id)){
			Unit unit = register.get(id);
			map.nodes[unit.x][unit.y].remove(id);
			register.remove(id);
			unit = null;
		}
		else{
			Log.err("Unit " + id + " not found in UnitMng register (remove())");
		}
	}
	
	public Unit get(int id){
		if(register.containsKey(id)){
			return register.get(id);
		}
		else{
			Log.err("Unit " + id + " not found in UnitMng register (findUnit())");
			return null;
		}
	}
	
	public Vector<Unit> get(int x, int y){
		return map.nodes[x][y].getAll();
	}
	
	public Vector<Unit> getPlayerUnits(int playerId){
		Vector<Unit> units = new Vector<Unit>();
		
		for(Unit unit: register.values()){
			if(unit.playerId == playerId){
				units.add(unit);
			}
		}
		
		return units;
	}
	
	public void changeUnit(Unit oldunit, Unit newunit) {
		if(register.containsKey(oldunit.id)){
			Unit tmp = register.get(oldunit.id);
			register.put(tmp.id, newunit);
			map.nodes[tmp.x][tmp.y].remove(tmp.id);
			map.nodes[tmp.x][tmp.y].add(newunit);
			tmp = null;
		}
	}
}
