package actions;

import java.util.HashMap;
import java.util.HashSet;

import game.GameLogic;

public class GameActions {
	
	protected GameLogic game;
	
	HashMap<Integer, HashMap<Integer, Action>> turnActions; // <turn, map<actionId, Action>>
	HashMap<Integer, HashSet<Integer>> teamActions;   		// <teamId, set<actionId>>
	HashMap<Integer, HashSet<Integer>> playerActions; 		// <playerId, set<actionId>>
	
	public GameActions(GameLogic gameLogic){
		this.game = gameLogic;
		
		this.turnActions = new HashMap<Integer, HashMap<Integer, Action>>();
		this.teamActions = new HashMap<Integer, HashSet<Integer>>();
		this.playerActions = new HashMap<Integer, HashSet<Integer>>();
	}

	public void addAction(int clientId, Action action) {
		int teamId = game.players.get(clientId).teamId;
		
		if(!turnActions.containsKey(action.turn)){
			turnActions.put(action.turn, new HashMap<Integer, Action>());
		}
		turnActions.get(action.turn).put(action.id, action);
		
		if(!teamActions.containsKey(teamId)){
			teamActions.put(teamId, new HashSet<Integer>());
		}
		teamActions.get(teamId).add(action.id);
		
		if(!playerActions.containsKey(clientId)){
			playerActions.put(clientId, new HashSet<Integer>());
		}
		playerActions.get(clientId).add(action.id);
	}
	
	// remove all team actions and unregister team
	public void unregisterTeam(int teamId){
		HashSet<Integer> actions = teamActions.remove(teamId);
		if(actions != null){
			for(Integer actionId: actions){
				actions.remove(actionId);
			}
		}
		
		System.gc();
	}
	
	// remove all player actions and unregister player
	public void unregisterPlayer(int playerId){
		HashSet<Integer> actions = playerActions.remove(playerId);
		if(actions != null){
			for(Integer actionId: actions){
				actions.remove(actionId);
			}
		}
		
		System.gc();
	}
	
	public void executeTurn(int turn, int teamId){
		//
	}
}
