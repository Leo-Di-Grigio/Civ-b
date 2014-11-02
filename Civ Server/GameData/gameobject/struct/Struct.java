package gameobject.struct;

import java.util.Vector;

import database.DB;
import gameobject.Building;
import gameobject.Unit;

public class Struct extends Building {

	public int workersCapacity;
	public Vector<Unit> workers;
	
	public Struct(int playerId, int type, int x, int y) {
		super(playerId, type, x, y);
		
		workersCapacity = DB.getWorkersCapacity(this.type);
		workers = new Vector<Unit>();
	}
}
