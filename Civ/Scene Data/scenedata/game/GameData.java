package scenedata.game;

import misc.Enums;
import painter.Painter;
import player.Player;
import player.Team;
import player.units.Unit;
import player.units.UnitsMng;
import tasks.Task;
import teamdata.GameTeamData;

public class GameData {
	
	// map
	public GameMap map;
	
	// team
	public GameTeamData team;
	
	// units
	public UnitsMng units;
	
	public GameData(GameMap map) {
		this.map = map;
		units = new UnitsMng(map);
	}

	public void addPlayer(Player player) {
		Painter.addTask(new Task(Enums.Task.GAME_MSG, "Player " + player.id + " name: " + player.name + " is joined"));
	}

	public void addTeam(Team team) {
		
	}
	
	public void addUnit(Unit unit) {
		
	}

	public void updPlayer(String data) {
		
	}

	public void updTeam(String data) {
		
	}

	public void updUnit(String data) {
		
	}
}
