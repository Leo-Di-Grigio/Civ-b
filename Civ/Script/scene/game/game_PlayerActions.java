package scene.game;

import player.units.Unit;
import scenedata.game.GameData;
import script.unit.unit_CityBuild;
import script.unit.unit_MoveTo;
import data.units.ConstUnits;
import misc.Const;
import misc.Log;
import gui.GUI;
import gui.elements.GuiElementButtonUnitAction;
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
			setButtonsAction(gui, gamedata, line);
		}
	}
	
	private static void setButtonsAction(GUI gui, GameData gamedata, TableLine line) {
		int unitId = Integer.parseInt(line.getCell(0));
		Unit unit = gamedata.units.getUnit(unitId);
		
		if(unit.playerId == gamedata.clientId){
			switch(gamedata.units.getUnit(unitId).type){
				case ConstUnits.unitNull:
					clearButtonsAction(gui);
					break;
				
				case ConstUnits.unitAvatar:
					unitSelectedAvatar(gui, gamedata, unitId);
					break;
					
				case ConstUnits.unitCity:
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
		// action 0 - "moveTo"
		GuiElementButtonUnitAction button0 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton0);
		button0.setActionIcon(Const.imgActionMoveto);
		button0.setVisible(true);
		button0.setScript(new unit_MoveTo(gamedata));
		
		// action 5 - "city build"
		GuiElementButtonUnitAction button5 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton5);
		button5.setActionIcon(Const.imgActionCityBuild);
		button5.setVisible(true);
		button5.setScript(new unit_CityBuild(gamedata, unitId));
	}
	
	private static void unitSelectedCity(GUI gui, GameData gamedata, int unitId){
		
	}
}
