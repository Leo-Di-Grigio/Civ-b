package teamdata;

import java.util.HashMap;

import player.Player;
import player.Team;

public class GamePlayersData {
	
	public HashMap<Integer, Team> teams;
	public HashMap<Integer, Player> players;
	
	public GamePlayersData(){
		teams = new HashMap<Integer, Team>();
		players = new HashMap<Integer, Player>();
		
		Team defaultteam = new Team();
		teams.put(-1, defaultteam);
	}
	
	public void addPlayer(Player player){
		players.put(player.id, player);
	}
	
	public void addTeam(Team team){
		teams.put(team.id, team);
	}
	
	public void updTeam(String data){
		String [] arr = data.split(":");
		int id = Integer.parseInt(arr[0]);
		
		if(teams.containsKey(id)){
			teams.get(id).updateObj(arr);
		}
	}
	
	public void updPlayer(String data){
		String [] arr = data.split(":");
		int id = Integer.parseInt(arr[0]);
		
		if(players.containsKey(id)){
			players.get(id).updateObj(arr);
		}
	}
	
	public void delTeam(int id){
		if(teams.containsKey(id)){
			teams.remove(id);
		}
	}
	
	public void delPlayer(int id){
		if(players.containsKey(id)){
			players.remove(id);
		}
	}
}
