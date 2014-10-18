package scene.game;

import gui.GUI;
import gui.elements.GuiElementTable;
import player.Player;
import player.Team;
import player.units.Unit;
import scenedata.game.GameData;

public class game_Data {

	public static void objPlayer(GUI gui, GameData gamedata, String data) {
		Player player = new Player(data);
		gamedata.addPlayer(player);
		
		if(gui != null){
			GuiElementTable table = (GuiElementTable)gui.get("players");
			table.sort(gamedata);
		}
	}

	public static void objTeam(GUI gui, GameData gamedata, String data) {
		Team team = new Team(data);
		gamedata.addTeam(team);
		
		if(gui != null){
			GuiElementTable table = (GuiElementTable)gui.get("players");
			table.sort(gamedata);
		}
	}
	
	public static void objUnit(GUI gui, GameData gamedata, String data){
		Unit unit = new Unit(data);
		gamedata.addUnit(unit);
	}

	public static void updPlayer(GUI gui, GameData gamedata, String data) {
		gamedata.updPlayer(data);
	}
	
	public static void updTeam(GUI gui, GameData gamedata, String data) {
		gamedata.updTeam(data);
	}
	
	public static void updUnit(GUI gui, GameData gamedata, String data) {
		gamedata.updUnit(data);
	}

	public static void delPlayer(GUI gui, GameData gamedata, String data) {
		int playerId = Integer.parseInt(data);
		gamedata.delPlayer(playerId);
		
		if(gui != null){
			GuiElementTable table = (GuiElementTable)gui.get("players");
			gamedata.users.players.remove(playerId);
			
			if(table != null){
				table.sort(gamedata);
			}
		}
	}

	public static void delTeam(GUI gui, GameData gamedata, String data) {
		
	}

	public static void delUnit(GUI gui, GameData gamedata, String data) {
		
	}
}
