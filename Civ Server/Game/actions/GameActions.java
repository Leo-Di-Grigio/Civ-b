package actions;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import net.Message;
import net.Message.Prefix;
import actions.Action.PlayerAction;
import algorithms.PathFinding;
import database.ConstAction;
import database.DB;
import gamedata.GameData;
import gameobject.GameObject;
import gameobject.Unit;

public class GameActions {

	private GameActionsPool pool;
	private GameData gamedata;
	
	public GameActions(GameData gamedata) {
		this.pool = new GameActionsPool(gamedata);
		this.gamedata = gamedata;
	}

	public void registerPlayer(int playerId) {
		this.pool.registerPlayer(playerId);
	}

	public void unregisterPlayer(int playerId) {
		this.pool.unregisterPlayer(playerId);
	}

	public void registerTeam(int teamId) {
		this.pool.registerTeam(teamId);
	}

	public void unregisterTeam(int teamId) {
		this.pool.unregisterTeam(teamId);
	}
	
	// Turn logic
	public void nextTurn() {
		this.pool.nextTurn();
	}

	public void teamActionsProcess(int teamId) throws IOException {
		this.pool.teamActionsProcess(teamId);
	}
	
	// Logic
	public void addPlayerAction(int clientId, String data) throws IOException {
		String [] arr = data.split(":");
		int action = Integer.parseInt(arr[0]);
		
		switch(action){
			case ConstAction.moveTo:
				actionMoveTo(clientId, arr);
				break;
			
			case ConstAction.cityBuild:
				actionCityBuild(clientId, arr);
				break;
				
			case ConstAction.mine:
				actionMine(clientId, arr);
				break;
				
			case ConstAction.interact:
				actionInteract(clientId, arr);
				break;
				
			default: 
				break;
		}
	}
	
	private void actionMoveTo(int clientId, String[] arr) throws IOException {
		// key:(int)objectId:(int)toX:(int)toY
		int objectId = Integer.parseInt(arr[1]);
		int toX = Integer.parseInt(arr[2]);
		int toY = Integer.parseInt(arr[3]);
		
		Unit unit = (Unit)gamedata.gameObjects.getObject(objectId);
		
		if(unit.playerId == clientId){
			ArrayList<Point> way = PathFinding.getPath(unit.x, unit.y, toX, toY, DB.getMovementType(unit.type), gamedata.map.height, gamedata.map.sizeX, gamedata.map.sizeY);
		
			if(way != null){
				// correct way
				pool.addAction(clientId, new Action(PlayerAction.UNIT_MOVE_TO, objectId, toX, toY));
				gamedata.broad.sendToTeam(gamedata.players.get(clientId).teamId, new Message(Prefix.PLAYER_ACTION, "" + ConstAction.moveTo + ":" + objectId + ":" + toX + ":" + toY));
			}
			else{
				// null way
				pool.addAction(clientId, new Action(PlayerAction.UNIT_MOVE_TO, objectId, unit.x, unit.y));
				gamedata.broad.sendToTeam(gamedata.players.get(clientId).teamId, new Message(Prefix.PLAYER_ACTION, "" + ConstAction.moveTo + ":" + objectId + ":" + unit.x + ":" + unit.y));
			}
			
			unit.movementPath = way;
		}
	}
	
	private void actionCityBuild(int clientId, String [] arr) throws IOException {
		// key:(int)objectId
		int objectId = Integer.parseInt(arr[1]);
		
		GameObject object = gamedata.gameObjects.getObject(objectId);
		if(DB.isUnit(object.type)){
			Unit unit = (Unit)object;
			unit.movementPath = null;
			gamedata.broad.sendToPlayerTeam(unit.playerId, unit.toMessageUpdate("clearway"));
		}
		
		pool.addAction(clientId, new Action(PlayerAction.UNIT_QUARTER_BUILD, objectId));
	}

	private void actionMine(int clientId, String[] arr) throws IOException {
		int objectId = Integer.parseInt(arr[1]);
		GameObject object = gamedata.gameObjects.getObject(objectId);
		
		if(DB.isUnit(object.type)){
			Unit unit = (Unit)object;
			unit.movementPath = null;
			gamedata.broad.sendToPlayerTeam(unit.playerId, unit.toMessageUpdate("clearway"));
		}
		pool.addAction(clientId, new Action(PlayerAction.UNIT_MINE, objectId));
	}
	
	private void actionInteract(int clientId, String [] arr) {
		int scenario = Integer.parseInt(arr[1]);
		
		switch (scenario) {
			case ConstAction.interactView:
				break;
				
			case ConstAction.interactAttack:
				int unitId = Integer.parseInt(arr[2]);
				int x = Integer.parseInt(arr[3]);
				int y = Integer.parseInt(arr[4]);
				pool.addAction(clientId, new Action(PlayerAction.UNIT_ATTACK, unitId, x, y));
				break;
				
			case ConstAction.interactTalk:
				break;
				
			case ConstAction.interactBuildNodeUpdate:
				break;
				
			case ConstAction.interactBuildCityBuilding:
				break;
				
			case ConstAction.interactRepair:
				break;
				
			case ConstAction.interactMine:
				break;
				
			case ConstAction.interactAssist:
				break;
				
			case ConstAction.interactWorkAt:
				break;
		}
	}
}
