package actions;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

import misc.Log;
import game.GameData;

public class GameActions {
	
	protected int TURN = 0;
	protected GameData gamedata;
	protected GameActionsLogic logic;
	
	private HashMap<Integer, HashMap<Integer, Action>> turnActions; // <turn, map<actionId, Action>>
	private HashMap<Integer, TreeSet<Integer>> teamActions;   		// <teamId, set<actionId>>
	private HashMap<Integer, TreeSet<Integer>> playerActions; 		// <playerId, set<actionId>>
	
	protected class InvertSort implements Comparator<Integer>{
		@Override
		public int compare(Integer a, Integer b) {
			if(a == b){
				return 0;
			}
			else{
				if(a < b){
					return -1;
				}
				else{
					return 1;
				}
			}
		}
	};
	
	public GameActions(GameData gamedata){
		this.gamedata = gamedata;
		this.logic = new GameActionsLogic(gamedata);
		
		this.turnActions = new HashMap<Integer, HashMap<Integer, Action>>();
		this.turnActions.put(TURN, new HashMap<Integer, Action>());
		
		this.teamActions = new HashMap<Integer, TreeSet<Integer>>();
		this.teamActions.put(0, new TreeSet<Integer>(new InvertSort()));
		
		this.playerActions = new HashMap<Integer, TreeSet<Integer>>();
	}

	public void addAction(int clientId, Action action) {
		action.turn = this.TURN;
		action.teamId = gamedata.players.get(clientId).teamId;
		action.playerId = clientId;
		
		turnActions.get(action.turn).put(action.id, action);
		teamActions.get(action.teamId).add(action.id);
		playerActions.get(clientId).add(action.id);
	}
	
	public void registerPlayer(int playerId){
		if(!playerActions.containsKey(playerId)){
			playerActions.put(playerId, new TreeSet<Integer>(new InvertSort()));
		}
	}
	
	public void registerTeam(int teamId){
		if(!teamActions.containsKey(teamId)){
			teamActions.put(teamId, new TreeSet<Integer>(new InvertSort()));
		}
	}
	
	// remove all team actions and unregister team
	public void unregisterTeam(int teamId){
		TreeSet<Integer> actions = teamActions.remove(teamId);
		if(actions != null){
			for(Integer actionId: actions){
				actions.remove(actionId);
			}
		}
		
		System.gc();
	}
	
	// remove all player actions and unregister player
	public void unregisterPlayer(int playerId){
		TreeSet<Integer> actions = new TreeSet<Integer>(playerActions.remove(playerId));
		if(actions != null){
			for(Integer actionId: actions){
				actions.remove(actionId);
			}
		}
		
		System.gc();
	}
	
	public void nextTurn(int clientId){
		Log.service("" + gamedata.gameId + " - Turn " + TURN);
		this.TURN++;
		turnActions.put(this.TURN, new HashMap<Integer, Action>());
		turn(clientId); // test
	}
	
	private void turn(int clientId){
		HashMap<Integer, Action> map = turnActions.get(this.TURN);
		TreeSet<Integer> set = teamActions.get(gamedata.players.get(clientId).teamId);
		
		for(Integer key: set){
			logic.execute(map.remove(key));
		}
	}
}
