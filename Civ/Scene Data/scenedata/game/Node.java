package scenedata.game;

import java.util.Vector;

import player.units.Unit;
import misc.Enums;

public class Node {
	
	// Land data
	public byte height = 0;
	
	// Units data
	private Vector<Unit> units;
	
	// Drawing data
	public Enums.Terrain terrainType;
	public int border;
	
	// Geology
	public byte geology;
	
	public Node() {
		units = new Vector<Unit>();
	}
	
	public void add(Unit unit){
		units.add(unit);
	}
	
	public Unit pop(int id){
		for(int i = 0; i < units.size(); ++i){
			if(units.get(i).id == id){
				Unit tmp = units.get(i);
				units.remove(i);
				return tmp;
			}
		}
		
		return null;
	}
	
	public void remove(int id){
		for(int i = 0; i < units.size(); ++i){
			if(units.get(i).id == id){
				units.remove(i);
				return;
			}
		}
	}
	
	public Vector<Unit> getAll(){
		return units;
	}
}
