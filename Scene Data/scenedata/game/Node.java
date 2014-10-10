package scenedata.game;

import java.util.Vector;

import units.Unit;

public class Node {
	
	// Land data
	public byte height = 3;
	
	// Units data
	private Vector<Unit> units;
	
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
