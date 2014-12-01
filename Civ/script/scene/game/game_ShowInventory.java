package scene.game;

import gui.elements.GuiElementInventory;

import java.io.IOException;

import player.units.Unit;
import player.units.UnitInventory;
import misc.Log;
import scenedata.game.GameData;
import script.gui.ScriptGui;
import tasks.Task;

public class game_ShowInventory extends ScriptGui {

	private GameData gamedata; 
	private int unitId;
	
	public game_ShowInventory(GameData gamedata, int unitId) {
		this.unitId = unitId;
		this.gamedata = gamedata;
	}
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute game_ShowInventory");
		GuiElementInventory window = (GuiElementInventory)task.sceneGui.get(scenegui_Game.uiInventory);
		if(window != null){
			if(window.getVisible()){
				window.setVisible(false);
			}
			else{
				Unit unit = gamedata.units.getUnit(unitId);
				
				if(unit != null){
					UnitInventory inventory = unit.inventory;
					
					if(inventory != null){
						window.setInventory(inventory);
						window.setVisible(true);				
					}
				}
			}
		}
	}
}
