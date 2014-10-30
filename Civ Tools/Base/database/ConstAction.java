package database;

public class ConstAction {
	
	// msg.prefix = Prefix.PLAYER_ACTION
	public static final int moveTo = 0; // msg.data = "ConstAction:unitId:x:y:"
	public static final int cityBuild = 1; // msg.data = "ConstAction:unitId"
	public static final int mine = 2; // msg.data = "ConstAction:unitId"
	
	// city actions
	public static final int cityBuildUnit = 1000; // msg.data = "ConstAction:unitId:unitType";
}
