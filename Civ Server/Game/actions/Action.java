package actions;

public class Action {

	private static int ID = 0;
	
	public static enum PlayerAction {
		UNIT_MOVE_TO, 
		UNIT_QUARTER_BUILD, 
		UNIT_BUILD_UNIT, 
		UNIT_MINE, 
		
		QUARTER_SPAWN_NOVICE,
	};
	
	// id
	public int id;
	public int turn;
	
	// action keys
	public PlayerAction prefix;
	public int teamId;
	public int playerId;
	public int objectId;
	
	// action location
	public int x;
	public int y;
	
	// additional data
	public int objectType;
	
	private Action(PlayerAction prefix){
		this.id = ID++;
		this.prefix = prefix;
	}
	
	public Action(PlayerAction prefix, int field) {
		this(prefix);
		
		switch (prefix) {
			case UNIT_QUARTER_BUILD:
				this.objectId = field;
				break;
			
			case UNIT_MINE:
				this.objectId = field;
				break;
			
			case QUARTER_SPAWN_NOVICE:
				this.objectId = field;
				break;
				
			default:
				break;
		}
	}
	
	public Action(PlayerAction prefix, int field1, int field2) {
		this(prefix);
		
		switch(prefix){
			case UNIT_BUILD_UNIT:
				this.objectId = field1;   // unit who build new unit
				this.objectType = field2; // new unit type to build
				break;
				
			default: 
				break;
		}
	}
	
	/**
	 *<b>UNIT_MOVE_TO</b>:<br>
	 *unitId.move -> (x, y);<br>
	 *1) field1 - objectId<br>
	 *2) field2 - x<br>
	 *3) field3 - y<br>
	 */
	public Action(PlayerAction prefix, int field1, int field2, int field3){
		this(prefix);
		
		switch(prefix){
		
			case UNIT_MOVE_TO:
				// unitId.move -> (x, y);
				this.objectId = field1;
				this.x = field2;
				this.y = field3;
				break;
				
			default: 
				break;
		}
	}
}