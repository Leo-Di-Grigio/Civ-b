package scenedata.game;

import player.Player;
import player.Team;
import player.units.Unit;
import player.units.UnitsMng;
import teamdata.GamePlayersData;

public class GameData {
	
	// map
	public GameMap map;
	
	// team
	public GamePlayersData users;
	
	// units
	public UnitsMng units;
	
	public GameData(GameMap map) {
		this.map = map;
		this.users = new GamePlayersData();
		this.units = new UnitsMng(map);
	}

	public void addPlayer(Player player) {
		users.addPlayer(player);
	}

	public void addTeam(Team team) {
		users.addTeam(team);
	}
	
	public void addUnit(Unit unit) {
		
	}

	public void updPlayer(String data) {
		users.updPlayer(data);
	}

	public void updTeam(String data) {
		users.updTeam(data);
	}

	public void updUnit(String data) {
		
	}
	
	public void delPlayer(int id){
		users.delPlayer(id);
	}
	
	public void delTeam(int id){
		users.delTeam(id);
	}
	
	public void delUnit(int id){
		
	}
}
