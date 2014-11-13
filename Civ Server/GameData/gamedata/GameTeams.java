package gamedata;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import misc.Log;
import net.Message;
import net.Message.Prefix;
import network.ClientPool;
import team.Team;

public class GameTeams {
	
	private int gameId;
	private HashMap<Integer, Team> list;
	private HashMap<Integer, HashSet<Integer>> players; // <teamId, Set<playerId> >;
	
	public boolean newTurn = true;
	private int turnIndex = 0;
	private Vector<Integer> turnQueue;
	
	public GameTeams(int gameId) throws IOException{
		this.gameId = gameId;
		
		list = new HashMap<Integer, Team>();
		players = new HashMap<Integer, HashSet<Integer>>();
		
		// Turn
		turnQueue = new Vector<Integer>();
		
		// add "No team"
		this.add(-1, null, null);
	}
	
	public int getTeamsCount(){
		return list.size();
	}
	
	public Set<Integer> getTeamsId() {
		return list.keySet();
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
		turnQueue.add(team.id);
		
		Log.service("Player ID: " + clientId + " created new team \"" + teamName + "\" in gameId: " + gameId);
		broad.sendToPlayers(team.toMessage());

		return team.id;
	}
	

	public Team getTeam(int teamId) {
		return list.get(teamId);
	}
	
	public void remove(int teamId, GameBroadcasting broad) throws IOException{
		if(teamId != 0 && list.containsKey(teamId)){
			list.remove(teamId);
			players.remove(teamId);
			
			for(int i = 0; i < turnQueue.size(); ++i){
				if(turnQueue.get(i) == teamId){
					turnQueue.remove(i);
				}
			}
			
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
	
	public boolean deleteTeamIfNoPlayers(int teamId, GameBroadcasting broad) throws IOException{
		if(getTeamSize(teamId) == 0){
			remove(teamId, broad);
			return true;
		}
		else{
			return false;
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
	
	public Point getSpawn(int teamId){
		if(list.containsKey(teamId)){
			return list.get(teamId).spawn;
		}
		else{
			return null;
		}
	}
	
	public void setSpawns(HashSet<Point> spawns){
		Iterator<Point> iter = spawns.iterator();
		Iterator<Integer> team = list.keySet().iterator();
		
		while(iter.hasNext()){
			list.get(team.next()).spawn = iter.next();
		}
	}
	
	public int getTurnedTeam(){
		if(turnQueue.size() == 0){
			return 0;
		}
		else{
			if(turnIndex < turnQueue.size()){
				return turnQueue.get(turnIndex);
			}
			else{
				return 0;
			}
		}
	}
	
	public int nextTeamTurn(){
		turnIndex++;
		
		if(turnIndex == turnQueue.size()){
			newTurn = true;
			turnIndex = 0;
		}
		
		return turnQueue.get(turnIndex);
	}
	
	public int getTeamOwner(int teamId){
		if(list.containsKey(teamId)){
			return list.get(teamId).ownerPlayerId;
		}
		else{
			return -1;
		}
	}
}
