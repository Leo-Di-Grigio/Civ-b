package game;

import java.io.IOException;
import java.util.HashMap;

import misc.Log;
import net.Message;
import net.Message.Prefix;
import network.ClientPool;
import player.Player;
import player.Team;

public class GameTeams {
	
	protected int gameId;
	protected HashMap<Integer, Team> teams;
	
	public GameTeams(int gameId){
		this.gameId = gameId;
		teams = new HashMap<Integer, Team>();
	}
	
	public void add(int clientId, String teamName, GameBroadcasting broad) throws IOException{
		for(Team item: teams.values()){
			if(item.ownerPlayerId == clientId){
				ClientPool.getClient(clientId).send(new Message(Prefix.REQ_TEAM_ERR, "team already created by this player"));
				return;
			}
			else{
				if(item.name.compareTo(teamName) == 0){
					ClientPool.getClient(clientId).send(new Message(Prefix.REQ_TEAM_ERR, "team already exist"));
					return;
				}
			}
		}

		Team team = new Team(clientId, teamName);
		teams.put(team.id, team);
		
		Log.service("Player ID: " + clientId + " created new team \"" + teamName + "\" in gameId: " + gameId);
		broad.send(team.toMessage());
		
		Player player = GamesMng.get(gameId).players.players.get(clientId);
		player.teamId = team.id;
		broad.send(player.toMessageUpdate("teamId"));
	}
	
	public void remove(int teamId, GameBroadcasting broad) throws IOException{
		if(teams.containsKey(teamId)){
			for(Player player: GamesMng.get(gameId).players.players.values()){
				if(player.teamId == teamId){
					player.teamId = -1;
				}
			}
			
			teams.remove(teamId);
			Log.service("TeamID: " + teamId + " in gameID: " + gameId + " removed");
			broad.send(new Message(Prefix.DEL_TEAM, "" + teamId));
		}
	}
	
	public void sendTeamList(int clientId) throws IOException {
		for(Team item: teams.values()){
			ClientPool.sendMsg(clientId, item.toMessage());
		}
	}
	
	public int getTeamSize(int teamId, HashMap<Integer, Player> players){
		if(teamId != -1 && teams.containsKey(teamId)){
			return teams.get(teamId).getTeamSize(players);
		}
		else{
			return -1;
		}
	}
}
