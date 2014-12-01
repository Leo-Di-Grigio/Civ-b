package gameobject.unit.stats;

import database.DB;

public class UnitStats {
	
	public int hp;
	public int power;
	
	public UnitStats(int unitType) {
		this.hp = DB.getUnitHP(unitType);
		this.power = DB.getUnitPower(unitType);
	}
}
