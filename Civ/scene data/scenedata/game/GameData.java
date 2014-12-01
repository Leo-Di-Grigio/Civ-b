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
	public boolean turn;
	
	// units
	public int clientId;
	public UnitsMng units;
	
	public GameData(int clientId, long seed, int mapSizeX, int mapSizeY, int tMin, int tMax, int landPercent) {
		this.users = new GamePlayersData();
		this.clientId = clientId;
		
		this.map = new GameMap(seed, mapSizeX, mapSizeY, tMin, tMax, landPercent);
		this.units = new UnitsMng(map);
		this.map.units = this.units; // sorry for bullshitcode t_t
	}

	public void addPlayer(Player player) {
		users.addPlayer(player);
	}

	public void addTeam(Team team) {
		users.addTeam(team);
	}
	
	public void addUnit(Unit unit) {
		units.addUnit(unit);
	}

	public void updPlayer(String data) {
		users.updPlayer(data);
	}

	public int updTeam(String data) {
		return users.updTeam(data);
	}

	public void updUnit(String data) {
		units.updUnit(data);
	}
	
	public void delPlayer(int id){
		users.delPlayer(id);
	}
	
	public void delTeam(int id){
		users.delTeam(id);
	}
	
	public void delUnit(int id){
		units.delUnit(id);
	}
}
