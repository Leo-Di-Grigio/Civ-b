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
	}
	
	public void addPlayer(Player player){
		players.put(player.id, player);
	}
	
	public void addTeam(Team team){
		teams.put(team.id, team);
	}
	
	public int updTeam(String data){
		String [] arr = data.split(":");
		int id = Integer.parseInt(arr[0]);
		
		if(teams.containsKey(id)){
			Team team = teams.get(id);
			
			if(arr[1].compareTo("id") == 0){
				// re-register Team (update "id" field)
				teams.remove(id);
				team.updateObj(arr);
				teams.put(team.id, team);
			}
			else{
				team.updateObj(arr);
			}
		}
		
		return id;
	}
	
	public void updPlayer(String data){
		String [] arr = data.split(":");
		int id = Integer.parseInt(arr[0]);
		
		if(players.containsKey(id)){	
			Player player = players.get(id);
			
			if(arr[1].compareTo("id") == 0){
				// re-register Player (update "id" field)
				players.remove(id);
				player.updateObj(arr);
				players.put(player.id, player);
			}
			else{
				player.updateObj(arr);
			}
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
