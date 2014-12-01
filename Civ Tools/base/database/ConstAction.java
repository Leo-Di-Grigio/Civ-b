package database;

public class ConstAction {
	
	// msg.prefix = Prefix.PLAYER_ACTION
	public static final int moveTo = 0; // msg.data = "ConstAction:unitId:x:y:"
	public static final int cityBuild = 1; // msg.data = "ConstAction:unitId"
	public static final int mine = 2; // msg.data = "ConstAction:unitId"
	public static final int interact = 3; // msg.data = "ConstAction:unitId:x:y"
	
	// interact mode
	public static final int intreactBreak = 0;
	public static final int interactView = 1;
	public static final int interactAttack = 2;
	public static final int interactTalk = 3;
	public static final int interactBuildNodeUpdate = 4;
	public static final int interactBuildCityBuilding = 5;
	public static final int interactRepair = 6;
	public static final int interactMine = 7;
	public static final int interactAssist = 8;
	public static final int interactWorkAt = 9; // at city building
}
