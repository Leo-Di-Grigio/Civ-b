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
		if(objectType > 0 && objectType < 1000){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean isBuilding(int objectType){
		if(objectType > 1000){
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
		return 0;
	}

	public static int getUnitPower(int unitType) {
		return 0;
	}
}
