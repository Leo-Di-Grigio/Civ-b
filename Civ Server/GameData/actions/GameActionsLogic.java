package actions;

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
	}
}
