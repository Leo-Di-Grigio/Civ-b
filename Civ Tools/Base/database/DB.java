package database;

import misc.ToolsEnums;


public class DB {

	// Const of unit types
	public static final int unitWaypoint = -1;
	public static final int unitNull = 0;
	public static final int unitAvatar = 1;
	public static final int unitNovice = 2;
	
	// cities
	public static final int buildingQuarter = 1000;
	
	// node update
	public static final int nodeupdNull = 9000;
	
	// items
	public static final int itemRecource = 0;
	
	// expirience
	public static final int expForMine = 10;
	
	// (const)food for new novice unit
	public static final int foodForNovice = 20; // how many food need for spawn new novice
	
	public static ToolsEnums.UnitMovementType getMovementType(int unitType){
		
		switch(unitType){
			case unitWaypoint:
				return ToolsEnums.UnitMovementType.NULL;
				
			case unitNull:
				return ToolsEnums.UnitMovementType.NULL;
				
			case unitAvatar:
				return ToolsEnums.UnitMovementType.GROUND;
				
			case unitNovice:
				return ToolsEnums.UnitMovementType.GROUND;
				
			default: 
				return ToolsEnums.UnitMovementType.NULL;
		}
	}
	
	public static int getMovementPoints(int unitType){
		
		switch(unitType){
			case unitWaypoint:
				return 0;
				
			case unitNull:
				return 0;
				
			case unitAvatar:
				return 5;
				
			case unitNovice:
				return 5;
				
			default: 
				return 0;
		}
	}
	
	public static String getUnitTitle(int unitType){
		
		switch (unitType) {
			// units
			case unitAvatar: return "Avatar";
			case unitNovice: return "Novice";
			
			// city
			case buildingQuarter: return "City";
			case unitWaypoint: return "misc_waypoint";
		
			// null
			case unitNull:
			default: return "unit_null";
		}
	}
	
	public static boolean isUnit(int objectType){
		if(objectType > 0 && objectType < buildingQuarter){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isNodeUpdata(int objectType){
		if(objectType >= nodeupdNull && objectType <= 9100){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isBuilding(int objectType){
		if(objectType > buildingQuarter && objectType < nodeupdNull){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isQuarter(int objectType){
		if(objectType == buildingQuarter){
			return true;
		}
		else{
			return false;
		}
	}

	public static int getFoodIncrease(int unitType) {
		switch(unitType){
			case buildingQuarter: return 2;
				
			default: return 0;
		}
	}

	public static int getScinceIncrease(int unitType) {
		switch(unitType){
			case buildingQuarter: return 0;
		
			default: return 0;
		}
	}

	public static int getCultureIncrease(int unitType) {
		switch(unitType){
			case buildingQuarter: return 0;
		
			default: return 0;
		}
	}

	public static int getWorkersCapacity(int unitType) {		
		switch(unitType){
			
			default: return 0;
		}
	}

	public static int getUnitHP(int unitType) {
		switch(unitType){
			case unitAvatar: return 30;
			case unitNovice: return 30;
		
			default: return 0;
		}
	}

	public static int getUnitPower(int unitType) {
		switch(unitType){
			case unitAvatar: return 6;
			case unitNovice: return 6;
		
			default: return 0;
		}
	}

	public static int getInventorySize(int unitType) {
		switch(unitType){
			case unitAvatar: return 5;
			case unitNovice: return 4;
			
			default: return 0;
		}
	}

	public static int getItemIcon(int itemType) {
		switch (itemType) {
			case itemRecource: return 1;
			default: return 0;
		}		
	}
	
	public static String getRecourceTitle(int recourceId){
		switch (recourceId) {
			default: return "Unnamed recource";
		}
	}
}
