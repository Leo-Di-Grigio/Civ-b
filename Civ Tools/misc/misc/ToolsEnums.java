package misc;

public class ToolsEnums {

	public static enum UnitMovementType {
		NULL,
		GROUND,
		SWIM,
		FLY;
		
		public boolean isPassable(byte height){
			// height == 0 -> sea
			// height > 0 -> land
			
			switch(this){
				case FLY: 
					return true;
				
				case GROUND: {
					if(height > 0){
						return true;
					}
				} break;
				
				case SWIM: {
					if(height == 0){
						return true;
					}
				} break;
				
				case NULL:
					return false;
					
				default:
					return false;
			}
			
			return false;
		}
	}
}
