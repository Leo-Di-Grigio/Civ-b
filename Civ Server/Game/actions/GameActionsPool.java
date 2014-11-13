package actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import net.Message;
import net.Message.Prefix;
import team.Team;
import database.DB;
import misc.Enums;
import misc.Log;
import gamedata.GameData;
import gameobject.GameObject;
import gameobject.Unit;
import gameobject.city.Quarter;

public class GameActionsPool {
	
	protected int TURN = 0;
	protected GameData gamedata;
	protected GameActionsLogic logic;
	
	private HashMap<Integer, HashMap<Integer, Action>> turnActions; 		   // <turn, map<actionId, Action>>
	private HashMap<Integer, HashMap<Integer, TreeSet<Integer>>> teamActions;  // <turn, map<teamId, set<actionId>>>
	private HashMap<Integer, HashMap<Integer, TreeSet<Integer>>> playerActions; // <turn, map<playerId, set<actionId>>>
	
	protected class InvertSort implements Comparator<Integer>{
		@Override
		public int compare(Integer a, Integer b) {
			if(a == b){
				return 0;
			}
			else{
				if(a < b){
					return 1;
				}
				else{
					return -1;
				}
			}
		}
	};
	
	public GameActionsPool(GameData gamedata){
		this.gamedata = gamedata;
		this.logic = new GameActionsLogic(gamedata, this);
		
		this.turnActions = new HashMap<Integer, HashMap<Integer, Action>>();
		this.turnActions.put(this.TURN, new HashMap<Integer, Action>());
		
		this.teamActions = new HashMap<Integer, HashMap<Integer, TreeSet<Integer>>>();
		this.teamActions.put(TURN, new HashMap<Integer, TreeSet<Integer>>());
		this.teamActions.get(TURN).put(0, new TreeSet<Integer>(new InvertSort()));
		
		this.playerActions = new HashMap<Integer, HashMap<Integer, TreeSet<Integer>>>();
	}

	public void addAction(int clientId, Action action) {
		action.turn = this.TURN + 1;
		action.teamId = gamedata.players.get(clientId).teamId;
		action.playerId = clientId;
		
		// Turn actions
		if(!turnActions.containsKey(action.turn)){
			turnActions.put(action.turn, new HashMap<Integer, Action>());
		}
		turnActions.get(action.turn).put(action.id, action);
		
		// Team actions
		if(!teamActions.containsKey(action.turn)){
			teamActions.put(action.turn, new HashMap<Integer, TreeSet<Integer>>());
		}
		if(!teamActions.get(action.turn).containsKey(action.teamId)){
			teamActions.get(action.turn).put(action.teamId, new TreeSet<Integer>(new InvertSort()));
		}
		teamActions.get(action.turn).get(action.teamId).add(action.id);
		
		// Player actions
		if(!playerActions.containsKey(action.turn)){
			playerActions.put(action.turn, new HashMap<Integer, TreeSet<Integer>>());
		}
		if(!playerActions.get(action.turn).containsKey(action.playerId)){
			playerActions.get(action.turn).put(action.playerId, new TreeSet<Integer>(new InvertSort()));
		}
		playerActions.get(action.turn).get(action.playerId).add(action.id);
	}
	
	public void registerPlayer(int playerId){
		if(!playerActions.containsKey(playerId)){
			playerActions.put(TURN, new HashMap<Integer, TreeSet<Integer>>());
			playerActions.get(TURN).put(playerId, new TreeSet<Integer>(new InvertSort()));
		}
	}
	
	public void registerTeam(int teamId){
		if(!teamActions.containsKey(teamId)){
			teamActions.put(TURN, new HashMap<Integer, TreeSet<Integer>>());
			teamActions.get(TURN).put(teamId, new TreeSet<Integer>(new InvertSort()));
		}
	}
	
	public void nextTurn(){
		this.TURN++;
	}
	
	// remove all team actions and unregister team
	public void unregisterTeam(int teamId){
		HashMap<Integer, TreeSet<Integer>> turnTeamActions = teamActions.get(TURN + 1);
		
		if(turnTeamActions != null){
			TreeSet<Integer> teamActionsIds = turnTeamActions.remove(teamId);
		
			if(teamActionsIds != null){
				HashMap<Integer, Action> turn = turnActions.get(TURN + 1);
				
				for(Integer actionId: teamActionsIds){
					turn.remove(actionId);
				}
			}
		
			System.gc();
		}
	}
	
	// remove all player actions and unregister player
	public void unregisterPlayer(int playerId){
		HashMap<Integer, TreeSet<Integer>> turnPlayeresActions = playerActions.get(TURN + 1);
		
		if(turnPlayeresActions != null){
			TreeSet<Integer> playerActionsIds = turnPlayeresActions.remove(playerId);
		
			if(playerActionsIds != null){
				HashMap<Integer, Action> turn = turnActions.get(TURN + 1);
				
				for(Integer actionId: playerActionsIds){
					turn.remove(actionId);
				}
			}
		
			System.gc();
		}
	}
	
	public void teamActionsProcess(int teamId) throws IOException{
		Log.service("" + gamedata.gameId + " - Turn " + TURN);
			
		newTeamTurn(teamId);
		turnActions(teamId);
	}
	
	private void newTeamTurn(int teamId) throws IOException{
		// reset all turn stats of team
		HashSet<Integer> players =  gamedata.teams.getPlayers(teamId);
		
		for(Integer playerId: players){
			HashSet<Integer> objects = gamedata.gameObjects.getPlayersObjects(playerId);
			
			// reset object turn stats
			for(Integer objectId: objects){
				GameObject object = gamedata.gameObjects.getObject(objectId);
				object.turnEnd = false;
				
				if(DB.isUnit(object.type)){
					Unit unit = (Unit)object;
					unit.resetMovementPoints();
				}
				
				if(DB.isQuarter(object.type)){
					// update quarter stats
					Quarter quarter = (Quarter)object;
					quarter.updateRecources();
					
					// get new quarter actions
					ArrayList<Action> newActions = quarter.getActions();
					for(Action action: newActions){
						addAction(quarter.playerId, action);
					}
				}
			}
		}
		
		Team team = gamedata.teams.getTeam(teamId);
		if(team != null){
			if(team.age == Enums.TeamAge.PREHISTORIC){
				team.scince++;
				
				if(team.scince % 10 == 0){
					team.scince -= 10;
					int techId = team.tech.learnPrehistory();
					
					if(techId != -1){
						gamedata.broad.sendToTeam(teamId, team.toMessageUpdate("tech"));
						gamedata.broad.sendToPlayers(new Message(Prefix.CHAT_MSG, "teamId: " + teamId + " learned techId: " + techId));
					}
				}
			}
		}
	}
	
	private void turnActions(int teamId) throws IOException{
		HashMap<Integer, Action> map = turnActions.get(TURN);
		
		if(map != null){
			TreeSet<Integer> keys = teamActions.get(TURN).get(teamId);

			if(keys != null){
				for(Integer actionId: keys){
					Action action = map.get(actionId);
					logic.execute(action);
				}
			}
		}
	}
}
