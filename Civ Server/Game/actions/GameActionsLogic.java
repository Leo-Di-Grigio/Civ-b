package actions;

import items.Item;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import database.DB;
import actions.Action.PlayerAction;
import map.Node;
import misc.Log;
import misc.Tools;
import gamedata.GameData;
import gameobject.GameObject;
import gameobject.Unit;
import gameobject.city.Quarter;
import gameobject.unit.Novice;

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
					
				case UNIT_QUARTER_BUILD:
					unitQuarterBuild(action);
					break;
					
				case UNIT_BUILD_UNIT:
					unitBuildUnit(action);
					break;
				
				case UNIT_MINE:
					unitMine(action);
					break;

				case UNIT_ATTACK:
					unitAttack(action);
					break;
					
				case QUARTER_SPAWN_NOVICE:
					quarterSpawnNovice(action);
					break;
				
				default:
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
	
	private void unitQuarterBuild(Action action) throws IOException {
		Unit unit = (Unit)gamedata.gameObjects.getObject(action.objectId);
		
		if(unit.type == DB.unitAvatar && !unit.turnEnd && unit.movementPath == null && unit.movementPoints > 0){
			Node node = gamedata.map.getNode(unit.x, unit.y);
			
			if(!node.isCity()){
				if(node.isNodeUpdate()){
					gamedata.gameObjects.removeObject(node.getNodeUpdateId(), gamedata.broad);
				}
				
				unit.turnEnd = true;	
				Quarter quarter = new Quarter(unit.playerId, unit.x, unit.y);
				gamedata.gameObjects.addObject(quarter, gamedata.broad);
			}
		}
	}
	
	private void unitBuildUnit(Action action) throws IOException {
		// build new unit
		GameObject object = gamedata.gameObjects.getObject(action.objectId);
		
		if(object != null && object.type == DB.buildingQuarter && !object.turnEnd){
			object.turnEnd = true;
			
			Unit newUnit = new Unit(object.playerId, action.objectType, object.x, object.y);
			gamedata.gameObjects.addObject(newUnit, gamedata.broad);
		}
	}
	

	private void unitMine(Action action) throws IOException {
		GameObject object = gamedata.gameObjects.getObject(action.objectId);
		
		if(object != null && !object.turnEnd && DB.isUnit(object.type)){
			Unit unit = (Unit)object;
			unit.turnEnd = true;
			
			// digg resource
			int recourceType = gamedata.map.geology[unit.x][unit.y];
			Item item = new Item(DB.itemRecource, recourceType);
			
			if(unit.inventory.addItem(item)){
				// success
				unit.exp += DB.expForMine;
				gamedata.broad.sendToPlayer(unit.playerId, unit.inventory.toMessageUpdate("items"));
			}
			else{
				// inventory is full
			}
		}
	}
	
	private void quarterSpawnNovice(Action action) throws IOException {
		Quarter quarter = (Quarter)gamedata.gameObjects.getObject(action.objectId);
		
		if(quarter != null){
			if(quarter.food >= DB.foodForNovice){
				quarter.food -= DB.foodForNovice; // pay food
				Novice novice = new Novice(action.playerId, quarter.x, quarter.y); // spawn novice
				gamedata.gameObjects.addObject(novice, gamedata.broad);
			}
		}
	}
	

	private void unitAttack(Action action) throws IOException {
		Unit unit = (Unit)gamedata.gameObjects.getObject(action.objectId);
		
		if(unit != null){
			int range = Tools.getRange(unit.x, unit.y, action.x, action.y, gamedata.map.sizeX);
			
			if(range > 1){
				// move to (x,y)
			}
			else{
				Node node = gamedata.map.getNode(action.x, action.y);
				ArrayList<Integer> nodeObjectsId = node.getAllObjects();
				
				if(nodeObjectsId != null && nodeObjectsId.size() > 0){
					for(int i = 0; i < nodeObjectsId.size(); ++i){
						GameObject object = gamedata.gameObjects.getObject(nodeObjectsId.get(i));
						
						if(DB.isUnit(object.type)){
							Unit target = (Unit)object;
							target.stats.hp -= unit.stats.power;
							
							if(target.stats.hp <= 0){ // kill the target
								gamedata.gameObjects.removeObject(target.id, gamedata.broad);
							}
							
							break;
						}
					}
				}
			}
		}
	}
}