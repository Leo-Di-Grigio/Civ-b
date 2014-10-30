package database;

import misc.ToolsEnums;


public class DB {

	// Const of unit types
	public static final int unitWaypoint = -1;
	public static final int unitNull = 0;
	public static final int unitAvatar = 1;
	public static final int unitRecruit = 2;
	
	// cities
	public static final int unitCity = 1000;
	
	public static ToolsEnums.UnitMovementType getMovementType(int unitType){
		
		switch(unitType){
			case unitWaypoint:
				return ToolsEnums.UnitMovementType.NULL;
				
			case unitNull:
				return ToolsEnums.UnitMovementType.NULL;
				
			case unitAvatar:
				return ToolsEnums.UnitMovementType.GROUND;
				
			case unitRecruit:
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
				
			case unitRecruit:
				return 5;
				
			default: 
				return 0;
		}
	}
	
	public static String getUnitTitle(int unitType){
		
		switch (unitType) {
			// units
			case unitAvatar: return "Avatar";
			case unitRecruit: return "Recrut";
			
			// city
			case unitCity: return "City";
			case unitWaypoint: return "misc_waypoint";
		
			// null
			case unitNull:
			default: return "unit_null";
		}
	}
}
