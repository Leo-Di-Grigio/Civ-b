package actions;

import player.units.Unit;
import misc.Log;
import game.GameData;

public class GameActionsLogic {
	
	protected GameData gamedata;
	
	public GameActionsLogic(GameData gamedata) {
		this.gamedata = gamedata;
	}

	
	public void execute(Action action){
		if(action != null){
			
			switch(action.prefix){
			
				case UNIT_MOVE_TO:
					unitMoveTo(action);
					break;
			}
		}
	}
	
	public void unitMoveTo(Action action) {
		Log.service("AtionID: " + action.id + " playerId: " + action.playerId + " pref: " + action.prefix);
		
		Unit unit = gamedata.units.getUnit(action.unitId);
		if(unit.way != null){
			// while(unit.movement > 0)
			// {
			// 	  //UDP send, continue movement
			// 	  borad.udpSend(unitWayUpdate)
			// 	  unit.movement--;
			// }
			// 
			// TCP send, movement end
			// unit.movementEnd = true;
			// broad.send(unitEndPosition);
		}
	}
}
