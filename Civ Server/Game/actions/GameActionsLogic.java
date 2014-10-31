package actions;

import java.awt.Point;
import java.io.IOException;

import database.DB;
import actions.Action.PlayerAction;
import misc.Log;
import game.GameData;
import gameobject.GameObject;

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
				
				case UNIT_MINE:
					unitMine(action);
					break;
			}
		}
	}

	private void unitMoveTo(Action action) throws IOException {		
		GameObject object = gamedata.gameObjects.getObject(action.objectId);
		
		if(!object.movementEnd && object.way != null && object.movementPoints > 0){
			object.movementEnd = true;
			
			Point endPoint = null;
			if(object.way.size() >= object.movementPoints){
				// move to a part way, remove completed way points
				
				for(int i = 0; i < object.movementPoints && i < object.way.size(); ++i){
					endPoint = object.way.remove(i);
				}
				
				// add movement continue to next turn if movement not complete
				gameactions.addAction(action.playerId, new Action(PlayerAction.UNIT_MOVE_TO, action.objectId, action.x, action.y));
			}
			else{
				// move to end of way and remove way
				endPoint = object.way.get(object.way.size() - 1);
				object.way = null;
			}
			
			object.x = endPoint.x;
			object.y = endPoint.y;
			gamedata.broad.sendToPlayers(object.toMessageUpdate("xy"));
		}
	}
	
	private void unitCityBuild(Action action) throws IOException {
		GameObject object = gamedata.gameObjects.getObject(action.objectId);
		
		if(object.type == DB.unitAvatar && !object.movementEnd && object.way == null && object.movementPoints > 0){
			object.movementEnd = true;
			
			GameObject city = new GameObject(object.playerId, DB.unitCity, object.x, object.y);
			gamedata.gameObjects.addUnit(city, gamedata.broad);
		}
	}
	
	private void unitBuildUnit(Action action) throws IOException {
		// build new unit
		GameObject object = gamedata.gameObjects.getObject(action.objectId);
		
		if(object != null && object.type == DB.unitCity && !object.movementEnd){
			object.movementEnd = true;
			
			GameObject newunit = new GameObject(object.playerId, action.unitType, object.x, object.y);
			gamedata.gameObjects.addUnit(newunit, gamedata.broad);
		}
	}
	

	private void unitMine(Action action) {
		GameObject object = gamedata.gameObjects.getObject(action.objectId);
		
		if(object != null){
			// 
		}
	}
}