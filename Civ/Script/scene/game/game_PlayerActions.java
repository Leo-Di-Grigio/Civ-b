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
import gui.elements.GuiElementTitle;

public class game_PlayerActions {

	public static void updateTableSelection(GUI gui, GameData gamedata, Unit unit) {
		Log.debug("Execute game_PlayerActions.updateTableSelection(..)");
		
		if(unit != null){
			selectUnit(gui, gamedata, unit);
		}
		else{
			clearButtonsAction(gui);
		}
	}

	private static void selectUnit(GUI gui, GameData gamedata, Unit unit) {
		Log.debug("Execute game_PlayerActions.selectUnit(..)");
	
		updateGui(gui, gamedata, unit);
		setButtonsAction(gui, gamedata, unit);
	}
	
	private static void updateGui(GUI gui, GameData gamedata, Unit unit) {
		clearButtonsAction(gui);
		
		GuiElementPane pane = (GuiElementPane)gui.get(scenegui_Game.uiInfopane);
		
		if(pane != null){
			GuiElementIcon icon = (GuiElementIcon)pane.getElement(scenegui_Game.uiInfopaneIcon);
			GuiElementTitle title = (GuiElementTitle)pane.getElement(scenegui_Game.uiInfopaneTitle1);
			
			if(icon != null){
				icon.setTexture(Recources.getUnitImage(unit.type));
			}
			
			if(title != null){
				title.setText(DB.getUnitTitle(unit.type) + " HP: " + unit.hp + "/" + DB.getUnitHP(unit.type));
			}
		}
	}

	private static void setButtonsAction(GUI gui, GameData gamedata, Unit unit) {
		if(unit.playerId == gamedata.clientId){
			switch(unit.type){
				case DB.unitNull:
					clearButtonsAction(gui);
					break;
				
				case DB.unitAvatar:
					unitSelectedAvatar(gui, gamedata, unit);
					break;
					
				case DB.unitNovice:
					unitSelectedNovice(gui, gamedata, unit);				
					break;
					
				case DB.buildingQuarter:
					unitSelectedCity(gui, gamedata, unit);
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
	
	private static void unitSelectedAvatar(GUI gui, GameData gamedata, Unit unit){
		buttonMoveTo(gui, gamedata, unit); // 0
		buttonInteract(gui, gamedata, unit); // 1
		buttonMine(gui, gamedata, unit.id); // 2
		buttonInventory(gui, gamedata, unit.id); // 4
		buttonCityBuild(gui, gamedata, unit.id); // 5
	}

	private static void unitSelectedNovice(GUI gui, GameData gamedata, Unit unit){
		buttonMoveTo(gui, gamedata, unit); // 0
		buttonInteract(gui, gamedata, unit); // 1
		buttonMine(gui, gamedata, unit.id); // 2
		buttonInventory(gui, gamedata, unit.id); // 4
	}

	private static void unitSelectedCity(GUI gui, GameData gamedata, Unit unit){
		
	}
	
	private static void buttonMoveTo(GUI gui, GameData gamedata, Unit unit){
		// action 0 - "moveTo"
		GuiElementButtonUnitAction button0 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton0);
		button0.setActionIcon(Const.imgActionMoveto);
		button0.setVisible(true);
		button0.setScript(new unit_MoveTo(gamedata, unit));
	}
	
	private static void buttonInteract(GUI gui, GameData gamedata, Unit unit) {
		// action 1 - "interact"
		GuiElementButtonUnitAction button1 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton1);
		button1.setActionIcon(Const.imgActionInteract);
		button1.setVisible(true);
		button1.setScript(new unit_Interact(gamedata, unit));
	}
	
	private static void buttonCityBuild(GUI gui, GameData gamedata, int unitId){
		// action 5 - "city build"
		GuiElementButtonUnitAction button5 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton5);
		button5.setActionIcon(Const.imgActionCityBuild);
		button5.setVisible(true);
		button5.setScript(new unit_CityBuild(gamedata, unitId));
	}
	
	private static void buttonMine(GUI gui, GameData gamedata, int unitId){
		// action 2 - "mine"
		GuiElementButtonUnitAction button2 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton2);
		button2.setActionIcon(Const.imgActionMine);
		button2.setVisible(true);
		button2.setScript(new unit_Mine(gamedata, unitId));
	}
	
	
	private static void buttonInventory(GUI gui, GameData gamedata, int unitId) {
		// action 4 - "inventory"
		GuiElementButtonUnitAction button4 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiButton4);
		button4.setActionIcon(Const.imgActionInventory);
		button4.setVisible(true);
		button4.setScript(new game_ShowInventory(gamedata, unitId));
	}
}
