package scene.game;

import player.Player;
import player.Team;
import player.units.Unit;
import scenedata.game.GameData;

public class game_Data {

	public static void objPlayer(GameData gamedata, String data) {
		Player player = new Player(data);
		gamedata.addPlayer(player);
	}

	public static void objTeam(GameData gamedata, String data) {
		Team team = new Team(data);
		gamedata.addTeam(team);
	}
	
	public static void objUnit(GameData gamedata, String data){
		Unit unit = new Unit(data);
		gamedata.addUnit(unit);
	}

	public static void updPlayer(GameData gamedata, String data) {
		gamedata.updPlayer(data);
	}
	
	public static void updTeam(GameData gamedata, String data) {
		gamedata.updTeam(data);
	}
	
	public static void updUnit(GameData gamedata, String data) {
		gamedata.updUnit(data);
	}
}
