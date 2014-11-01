package database;

import misc.ToolsEnums;


public class DB {

	// Const of unit types
	public static final int unitWaypoint = -1;
	public static final int unitNull = 0;
	public static final int unitAvatar = 1;
	public static final int unitNovice = 2;
	
	// cities
	public static final int buildingCity = 1000;
	
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
			case buildingCity: return "City";
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
	
	public static boolean isBuildint(int objectType){
		if(objectType >= 1000){
			return true;
		}
		else{
			return false;
		}
	}
}
