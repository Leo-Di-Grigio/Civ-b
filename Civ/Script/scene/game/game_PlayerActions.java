package scene.game;

import player.units.Unit;
import recources.Recources;
import scenedata.game.GameData;
import script.unit.unit_CityBuild;
import script.unit.unit_Mine;
import script.unit.unit_MoveTo;
import database.DB;
import misc.Const;
import misc.Log;
import gui.GUI;
import gui.elements.GuiElementButtonUnitAction;
import gui.elements.GuiElementIcon;
import gui.elements.GuiElementPane;
import gui.elements.GuiElementTable;
import gui.misc.TableLine;

public class game_PlayerActions {

	public static void updateTableSelection(GUI gui, GameData gamedata, GuiElementTable table) {
		Log.debug("Execute game_PlayerActions.updateTableSelection(..)");
		
		if(table != null){
			switch(table.getTitle()){
				case scenegui_Game.uiUnitSelect:
					selectUnit(gui, gamedata, table);
					break;
					
				default:
					clearButtonsAction(gui);
					break;
			}
		}
	}

	private static void selectUnit(GUI gui, GameData gamedata, GuiElementTable table) {
		Log.debug("Execute game_PlayerActions.selectUnit(..)");
		
		TableLine line = table.getSelectedLine();
		
		if(line == null){
			clearButtonsAction(gui);
		}
		else{
			updateGui(gui, gamedata, line);
			setButtonsAction(gui, gamedata, line);
		}
	}
	
	private static void updateGui(GUI gui, GameData gamedata, TableLine line) {
		clearButtonsAction(gui);
		
		GuiElementPane pane = (GuiElementPane)gui.get(scenegui_Game.uiInfopane);
		
		if(pane != null){
			GuiElementIcon icon = (GuiElementIcon)pane.getElement(scenegui_Game.uiInfopaneIcon);
			GuiElementTable unitSelectTable = (GuiElementTable)gui.get(scenegui_Game.uiUnitSelect);
			
			if(icon != null){
				int selectedUnitType = Integer.parseInt(unitSelectTable.getSelectedLine().getCell(2)); // get unit.type
				icon.setTexture(Recources.getUnitImage(selectedUnitType));
			}
		}
	}

	private static void setButtonsAction(GUI gui, GameData gamedata, TableLine line) {
		int unitId = Integer.parseInt(line.getCell(0));
		Unit unit = gamedata.units.getUnit(unitId);
		
		if(unit.playerId == gamedata.clientId){
			switch(gamedata.units.getUnit(unitId).type){
				case DB.unitNull:
					clearButtonsAction(gui);
					break;
				
				case DB.unitAvatar:
					unitSelectedAvatar(gui, gamedata, unitId);
					break;
					
				case DB.unitNovice:
					unitSelectedRecruit(gui, gamedata, unitId);				
					break;
					
				case DB.buildingQuarter:
					unitSelectedCity(gui, gamedata, unitId);
					break;
					
				default:
					clearButtonsAction(gui);
					break;
			}
		}
		else{
			clearButtonsAction(gui); // it's not your unit :)
		}
	}

	public static void clearButtonsAction(GUI gui){
		GuiElementButtonUnitAction button0 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton0);
		GuiElementButtonUnitAction button1 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton1);
		GuiElementButtonUnitAction button2 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton2);
		GuiElementButtonUnitAction button3 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton3);
		GuiElementButtonUnitAction button4 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton4);
		GuiElementButtonUnitAction button5 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton5);
		
		button0.setActionIcon(Const.imgVoid);
		button1.setActionIcon(Const.imgVoid);
		button2.setActionIcon(Const.imgVoid);
		button3.setActionIcon(Const.imgVoid);
		button4.setActionIcon(Const.imgVoid);
		button5.setActionIcon(Const.imgVoid);
		
		button0.setScript(null);
		button1.setScript(null);
		button2.setScript(null);
		button3.setScript(null);
		button4.setScript(null);
		button5.setScript(null);
		
		button0.setVisible(false);
		button1.setVisible(false);
		button2.setVisible(false);
		button3.setVisible(false);
		button4.setVisible(false);
		button5.setVisible(false);
	}
	
	private static void unitSelectedAvatar(GUI gui, GameData gamedata, int unitId){
		buttonMoveTo(gui, gamedata); // 0
		buttonMine(gui, gamedata, unitId); // 1
		buttonCityBuild(gui, gamedata, unitId); // 5
	}
	
	private static void unitSelectedRecruit(GUI gui, GameData gamedata, int unitId){
		buttonMoveTo(gui, gamedata); // 0
	}
	
	private static void unitSelectedCity(GUI gui, GameData gamedata, int unitId){
		
	}
	
	private static void buttonMoveTo(GUI gui, GameData gamedata){
		// action 0 - "moveTo"
		GuiElementButtonUnitAction button0 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton0);
		button0.setActionIcon(Const.imgActionMoveto);
		button0.setVisible(true);
		button0.setScript(new unit_MoveTo(gamedata));
	}
	
	private static void buttonCityBuild(GUI gui, GameData gamedata, int unitId){
		// action 5 - "city build"
		GuiElementButtonUnitAction button5 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton5);
		button5.setActionIcon(Const.imgActionCityBuild);
		button5.setVisible(true);
		button5.setScript(new unit_CityBuild(gamedata, unitId));
	}
	
	private static void buttonMine(GUI gui, GameData gamedata, int unitId){
		// action 1 - "mine"
		GuiElementButtonUnitAction button1 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton1);
		button1.setActionIcon(Const.imgActionMine);
		button1.setVisible(true);
		button1.setScript(new unit_Mine(gamedata, unitId));
	}
}
