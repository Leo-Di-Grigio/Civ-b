package scene.game;

import database.ConstAction;
import misc.Enums;
import misc.Environment;
import gui.GUI;
import gui.elements.GuiElementButton;
import gui.elements.GuiElementTable;
import painter.Painter;
import player.Player;
import player.Team;
import player.units.Unit;
import scene.prepare.scenegui_Prepare;
import scenedata.game.GameData;
import script.unit.unit_MoveTo;

public class game_Data {

	public static void objPlayer(GUI gui, GameData gamedata, String data) {
		Player player = new Player(data);
		gamedata.addPlayer(player);
		
		if(Painter.currentSceneTitle == Enums.Scene.PREPEARE && gui != null){
			GuiElementTable table = (GuiElementTable)gui.get(scenegui_Prepare.uiPlayersTable);
			table.sort(gamedata);
		}
	}

	public static void objTeam(GUI gui, GameData gamedata, String data) {
		Team team = new Team(data);
		gamedata.addTeam(team);
		
		if(Painter.currentSceneTitle == Enums.Scene.PREPEARE && gui != null){
			GuiElementTable table = (GuiElementTable)gui.get(scenegui_Prepare.uiPlayersTable);
			table.sort(gamedata);
		}
	}
	
	public static void objUnit(GUI gui, GameData gamedata, String data){
		Unit unit = new Unit(data);
		gamedata.addUnit(unit);
		
		if(Painter.currentSceneTitle == Enums.Scene.PREPEARE){
			Environment.moveCameraToUnit(unit);
		}
	}

	public static void updPlayer(GUI gui, GameData gamedata, String data) {
		gamedata.updPlayer(data);
		
		if(Painter.currentSceneTitle == Enums.Scene.PREPEARE && gui != null){
			GuiElementTable table = (GuiElementTable)gui.get(scenegui_Prepare.uiPlayersTable);
			table.sort(gamedata);
			
			GuiElementButton button = (GuiElementButton)gui.get(scenegui_Prepare.uiButtonReadyCheck);
			if(gamedata.users.players.get(gamedata.clientId).teamId == 0){
				button.setVisible(false);
			}
			else{
				button.setVisible(true);
			}
		}
	}
	
	public static void updTeam(GUI gui, GameData gamedata, String data) {
		gamedata.updTeam(data);
		
		if(Painter.currentSceneTitle == Enums.Scene.PREPEARE && gui != null){
			GuiElementTable table = (GuiElementTable)gui.get(scenegui_Prepare.uiPlayersTable);
			table.sort(gamedata);
		}
	}
	
	public static void updUnit(GUI gui, GameData gamedata, String data) {
		gamedata.updUnit(data);
	}

	public static void delPlayer(GUI gui, GameData gamedata, String data) {
		int playerId = Integer.parseInt(data);
		gamedata.delPlayer(playerId);
		
		if(Painter.currentSceneTitle == Enums.Scene.PREPEARE && gui != null){
			GuiElementTable table = (GuiElementTable)gui.get(scenegui_Prepare.uiPlayersTable);
			
			if(table != null){
				table.sort(gamedata);
			}
		}
	}

	public static void delTeam(GUI gui, GameData gamedata, String data) {
		int teamId = Integer.parseInt(data);
		gamedata.delTeam(teamId);
		
		if(Painter.currentSceneTitle == Enums.Scene.PREPEARE && gui != null){
			GuiElementTable table = (GuiElementTable)gui.get(scenegui_Prepare.uiPlayersTable);
			
			if(table != null){
				table.sort(gamedata);
			}
		}
	}

	public static void delUnit(GUI gui, GameData gamedata, String data) {
		int unitId = Integer.parseInt(data);
		gamedata.delUnit(unitId);
	}

	public static void playerAction(GUI gui, GameData gamedata, String data) {
		String [] arr = data.split(":");
		int action = Integer.parseInt(arr[0]);
		
		switch(action){
			case ConstAction.moveTo:
				unit_MoveTo.addWay(gamedata, arr);
				break;
		}
	}
}
