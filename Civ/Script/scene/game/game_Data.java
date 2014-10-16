package scene.game;

import misc.Log;
import gui.GUI;
import gui.elements.GuiElementTable;
import gui.misc.TableLine;
import player.Player;
import player.Team;
import player.units.Unit;
import scenedata.game.GameData;

public class game_Data {

	public static void objPlayer(GUI gui, GameData gamedata, String data) {
		Log.debug("Execute game_Data - Player join");
		
		Player player = new Player(data);
		gamedata.addPlayer(player);
		
		if(gui != null){
			GuiElementTable table = (GuiElementTable)gui.get("players");
			
			if(table != null){
				TableLine line = new TableLine(table.getCollumns());
				line.setCell(0, ""+player.id);
				line.setCell(1, player.name);
				table.add(line);
			}
		}
	}

	public static void objTeam(GUI gui, GameData gamedata, String data) {
		Team team = new Team(data);
		gamedata.addTeam(team);
		
		if(gui != null){
			
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
			
			if(table != null){
				table.remove(0, ""+playerId);
			}
		}
	}

	public static void delTeam(GUI gui, GameData gamedata, String data) {
		
	}

	public static void delUnit(GUI gui, GameData gamedata, String data) {
		
	}
}
