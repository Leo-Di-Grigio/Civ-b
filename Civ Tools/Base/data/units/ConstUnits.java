package data.units;

import misc.ToolsEnums;

public class ConstUnits {
	
	// Const of unit types
	public static final int unitWaypoint = -1;
	public static final int unitNull = 0;
	public static final int unitAvatar = 1; 
	
	public static ToolsEnums.UnitMovementType getMovementType(int unitType){
		
		switch(unitType){
			case unitWaypoint:
				return ToolsEnums.UnitMovementType.NULL;
				
			case unitNull:
				return ToolsEnums.UnitMovementType.NULL;
				
			case unitAvatar:
				return ToolsEnums.UnitMovementType.GROUND;
				
			default: 
				return ToolsEnums.UnitMovementType.NULL;
		}
	}
}
