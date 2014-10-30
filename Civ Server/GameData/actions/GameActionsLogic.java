package actions;

import java.awt.Point;
import java.io.IOException;

import database.DB;
import actions.Action.PlayerAction;
import player.units.Unit;
import misc.Log;
import game.GameData;

public class GameActionsLogic {
	
	protected GameActions gameactions;
	protected GameData gamedata;
	
	public GameActionsLogic(GameData gamedata, GameActions actions) {
		this.gamedata = gamedata;
		this.gameactions = actions;
	}

	
	public void execute(Action action) throws IOException{
		if(action != null){
			Log.service("ActionID: " + action.id + " playerId: " + action.playerId + " pref: " + action.prefix);
			
			switch(action.prefix){
			
				case UNIT_MOVE_TO:
					unitMoveTo(action);
					break;
					
				case UNIT_CITY_BUILD:
					unitCityBuild(action);
					break;
					
				case UNIT_BUILD_UNIT:
					unitBuildUnit(action);
					break;
			}
		}
	}

	private void unitMoveTo(Action action) throws IOException {		
		Unit unit = gamedata.units.getUnit(action.unitId);
		
		if(!unit.movementEnd && unit.way != null && unit.movementPoints > 0){
			unit.movementEnd = true;
			
			Point endPoint = null;
			if(unit.way.size() >= unit.movementPoints){
				// move to a part way, remove completed way points
				
				for(int i = 0; i < unit.movementPoints && i < unit.way.size(); ++i){
					endPoint = unit.way.remove(i);
				}
				
				// add movement continue to next turn if movement not complete
				gameactions.addAction(action.playerId, new Action(PlayerAction.UNIT_MOVE_TO, action.unitId, action.x, action.y));
			}
			else{
				// move to end of way and remove way
				endPoint = unit.way.get(unit.way.size() - 1);
				unit.way = null;
			}
			
			unit.x = endPoint.x;
			unit.y = endPoint.y;
			gamedata.broad.sendToPlayers(unit.toMessageUpdate("xy"));
		}
	}
	
	private void unitCityBuild(Action action) throws IOException {
		Unit unit = gamedata.units.getUnit(action.unitId);
		
		if(unit.type == DB.unitAvatar && !unit.movementEnd && unit.way == null && unit.movementPoints > 0){
			unit.movementEnd = true;
			
			Unit city = new Unit(unit.playerId, DB.unitCity, unit.x, unit.y);
			gamedata.units.addUnit(city, gamedata.broad);
		}
	}
	
	private void unitBuildUnit(Action action) throws IOException {
		// build new unit
		Unit unit = gamedata.units.getUnit(action.unitId);
		
		if(unit.type == DB.unitCity && !unit.movementEnd){
			unit.movementEnd = true;
			
			Unit newunit = new Unit(unit.playerId, action.unitType, unit.x, unit.y);
			gamedata.units.addUnit(newunit, gamedata.broad);
		}
	}

}