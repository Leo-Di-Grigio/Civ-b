package gameobject;

import java.awt.Point;
import java.util.ArrayList;
import database.DB;
import gameobject.unit.stats.UnitSkills;
import gameobject.unit.stats.UnitStats;
import gameobject.unit.stats.UntiInventory;

public class Unit extends GameObject {

	public UnitSkills skills;
	public UnitStats stats;
	public UntiInventory inventory;

	// movement
	public ArrayList<Point> movementPath;
	public int movementPoints;
	
	// expirience
	public int exp;
	public int level;
	
	public Unit(int playerId, int type, int x, int y) {
		super(playerId, type, x, y);

		this.skills = new UnitSkills();
		this.stats = new UnitStats(this.type);
		this.inventory = new UntiInventory(this.id, DB.getInventorySize(this.type));
		
		// movement
		this.movementPath = null;
		this.movementPoints = DB.getMovementPoints(type);
	}

	public void resetMovementPoints() {
		this.movementPoints = DB.getMovementPoints(this.type);
	}
}
