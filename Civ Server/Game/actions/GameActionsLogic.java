package actions;

import java.awt.Point;
import java.io.IOException;

import database.DB;
import actions.Action.PlayerAction;
import misc.Log;
import game.GameData;
import gameobject.GameObject;
import gameobject.Unit;
import gameobject.city.City;

public class GameActionsLogic {
	
	protected GameActionsPool gameactions;
	protected GameData gamedata;
	
	public GameActionsLogic(GameData gamedata, GameActionsPool actions) {
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
				
				case UNIT_MINE:
					unitMine(action);
					break;
			}
		}
	}

	private void unitMoveTo(Action action) throws IOException {		
		Unit unit = (Unit)gamedata.gameObjects.getObject(action.objectId);
		
		if(!unit.turnEnd && unit.movementPath != null && unit.movementPoints > 0){
			unit.turnEnd = true;
			
			Point endPoint = null;
			if(unit.movementPath.size() > unit.movementPoints){
				// move to a part way, remove completed way points
				
				for(int i = 0; i < unit.movementPoints && i < unit.movementPath.size(); ++i){
					endPoint = unit.movementPath.remove(i);
					unit.movementPoints--;
				}
				
				// add movement continue to next turn if movement not complete
				gameactions.addAction(action.playerId, new Action(PlayerAction.UNIT_MOVE_TO, action.objectId, action.x, action.y));
			}
			else{
				// move to end of way and remove way
				endPoint = unit.movementPath.get(unit.movementPath.size() - 1);
				unit.movementPath = null;
			}
			
			unit.x = endPoint.x;
			unit.y = endPoint.y;
			gamedata.broad.sendToPlayers(unit.toMessageUpdate("xy"));
		}
	}
	
	private void unitCityBuild(Action action) throws IOException {
		Unit unit = (Unit)gamedata.gameObjects.getObject(action.objectId);
		
		if(unit.type == DB.unitAvatar && !unit.turnEnd && unit.movementPath == null && unit.movementPoints > 0){
			unit.turnEnd = true;
			
			City city = new City(unit.playerId, unit.x, unit.y);
			gamedata.gameObjects.addObject(city, gamedata.broad);
		}
	}
	
	private void unitBuildUnit(Action action) throws IOException {
		// build new unit
		GameObject object = gamedata.gameObjects.getObject(action.objectId);
		
		if(object != null && object.type == DB.buildingCity && !object.turnEnd){
			object.turnEnd = true;
			
			Unit newUnit = new Unit(object.playerId, action.objectType, object.x, object.y);
			gamedata.gameObjects.addObject(newUnit, gamedata.broad);
		}
	}
	

	private void unitMine(Action action) {
		GameObject object = gamedata.gameObjects.getObject(action.objectId);
		
		if(object != null){
			// 
		}
	}
}