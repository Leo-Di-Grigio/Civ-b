package game;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import misc.Log;
import net.Message;
import net.Message.Prefix;
import network.ClientPool;
import player.Team;

public class GameTeams {
	
	private int gameId;
	private HashMap<Integer, Team> list;
	private HashMap<Integer, HashSet<Integer>> players; // <teamId, Set<playerId> >;
	
	public GameTeams(int gameId) throws IOException{
		this.gameId = gameId;
		
		list = new HashMap<Integer, Team>();
		players = new HashMap<Integer, HashSet<Integer>>();
		
		// add "No team"
		this.add(-1, null, null);
	}
	
	public int add(int clientId, String teamName, GameBroadcasting broad) throws IOException{
		if(clientId == -1){
			// create default team
			Team team = new Team();
			list.put(team.id, team);
			players.put(team.id, new HashSet<Integer>());
			return 0;
		}
		
		// create custom team
		for(Team item: list.values()){
			if(item.ownerPlayerId == clientId){
				ClientPool.getClient(clientId).send(new Message(Prefix.REQ_TEAM_ERR, "team already created by this player"));
				return -1;
			}
			else{
				if(item.name.compareTo(teamName) == 0){
					ClientPool.getClient(clientId).send(new Message(Prefix.REQ_TEAM_ERR, "team already exist"));
					return -1;
				}
			}
		}

		Team team = new Team(clientId, teamName);
		list.put(team.id, team);
		players.put(team.id, new HashSet<Integer>());
		
		Log.service("Player ID: " + clientId + " created new team \"" + teamName + "\" in gameId: " + gameId);
		broad.sendToPlayers(team.toMessage());

		return team.id;
	}
	
	public void remove(int teamId, GameBroadcasting broad) throws IOException{
		if(teamId != 0 && list.containsKey(teamId)){
			list.remove(teamId);
			players.remove(teamId);
			Log.service("TeamID: " + teamId + " in gameID: " + gameId + " removed");
			broad.sendToPlayers(new Message(Prefix.DEL_TEAM, "" + teamId));
		}
	}
	
	public void sendTeamList(int clientId) throws IOException {
		for(Team item: list.values()){
			ClientPool.sendMsg(clientId, item.toMessage());
		}
	}
	
	public void registerPlayer(int teamId, int playerId){
		if(players.containsKey(teamId)){
			players.get(teamId).add(playerId);
		}
		else{
			Log.err("Try to register player ID: " + playerId + " in team ID: " + teamId);
		}
	}
	
	public boolean unregisterPlayer(int teamId, int playerId){
		if(players.containsKey(teamId)){
			players.get(teamId).remove(playerId);
			return true;
		}
		else{
			return false;
		}
	}
	
	public HashSet<Integer> getPlayers(int teamId){
		return players.get(teamId);
	}
	
	public void deleteTeamIfNoPlayers(int teamId, GameBroadcasting broad) throws IOException{
		if(getTeamSize(teamId) == 0){
			remove(teamId, broad);
		}
	}
	
	public int getTeamSize(int teamId){
		if(teamId != 0 && players.containsKey(teamId)){
			return players.get(teamId).size();
		}
		else{
			return -1;
		}
	}
}
