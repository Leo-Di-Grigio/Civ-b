package gameobject.building.stats;

import database.DB;

public class BuildingStats {

	// stats
	public int foodIncrease = 0;
	public int scinceIncrease = 0;
	public int cultureIncrease = 0;
	
	public BuildingStats(int unitType) {		
		foodIncrease = DB.getFoodIncrease(unitType);
		scinceIncrease = DB.getScinceIncrease(unitType);
		cultureIncrease = DB.getCultureIncrease(unitType);
	}
}
