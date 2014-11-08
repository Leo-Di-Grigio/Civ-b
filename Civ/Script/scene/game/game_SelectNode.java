package scene.game;

import java.util.ArrayList;

import java.util.HashSet;

import gui.GUI;
import gui.elements.GuiElementIcon;
import gui.elements.GuiElementInventory;
import gui.elements.GuiElementPane;
import gui.elements.GuiElementTitle;
import gui.elements.GuiElementUnits;
import painter.Painter;
import player.units.Unit;
import misc.Enums;
import misc.Environment;
import misc.Log;
import scenedata.game.GameData;
import scenedata.game.Node;
import script.Script;
import tasks.Task;

public class game_SelectNode extends Script {

	public static void selectNode(GameData gamedata) {
		int nodeX = Environment.nodeSelectedX;
		int nodeY = Environment.nodeSelectedY;
		int mapY = gamedata.map.sizeY;
		
		Log.debug("Execute game_SelectNode (" + nodeX + "," + nodeY + ")");

		if(nodeY >= 0 && nodeY < mapY){
			Painter.addTask(new Task(Enums.Task.GAME_SELECT_NODE, "" + nodeX +":" + nodeY + ":" + gamedata.clientId));
		}
		else{
			Painter.addTask(new Task(Enums.Task.GAME_SELECT_NODE, null));
		}
	}

	public static void updateGuiData(GUI gui, GameData gamedata, String data) {
		GuiElementPane pane = (GuiElementPane)gui.get(scenegui_Game.uiInfopane);
		
		GuiElementInventory window = (GuiElementInventory)gui.get(scenegui_Game.uiInventory);
		if(window != null){
			window.setVisible(false);
		}
		
		if(pane != null){
			GuiElementIcon icon = (GuiElementIcon)pane.getElement(scenegui_Game.uiInfopaneIcon);
			GuiElementTitle title1 = (GuiElementTitle)pane.getElement(scenegui_Game.uiInfopaneTitle0);
			GuiElementTitle title2 = (GuiElementTitle)pane.getElement(scenegui_Game.uiInfopaneTitle1);
			GuiElementTitle title3 = (GuiElementTitle)pane.getElement(scenegui_Game.uiInfopaneTitle2);
			GuiElementUnits unitSelectTable = (GuiElementUnits)gui.get(scenegui_Game.uiUnitSelect);
			
			String [] arr = data.split(":");
			int nodeX = Integer.parseInt(arr[0]);
			int nodeY = Integer.parseInt(arr[1]);
			int clientId = Integer.parseInt(arr[2]);
			
			Node node = gamedata.map.map[nodeX][nodeY];
			HashSet<Integer> units = null;
			
			if(node != null){
				units = node.getAll();
			}
			
			// Node map info
			if(title1 != null){
				if(node == null){
					title1.setText("");
				}
				else{
					title1.setText("Height in x: " + nodeX + " y: " + nodeY + " = " + node.height);
				}
			}
			
			// Node units
			if(title2 != null){
				if(node == null){
					title2.setText("");
				}
				else{
					if(units == null){
						if(unitSelectTable != null){
							unitSelect(null, unitSelectTable, gamedata, clientId, icon);
							game_PlayerActions.clearButtonsAction(gui);
						}
						title2.setText("");
					}
					else{
						if(unitSelectTable != null){
							if(units.size() > 0){
								unitSelect(units, unitSelectTable, gamedata, clientId, icon);
							}
							else{
								unitSelect(null, unitSelectTable, gamedata, clientId, icon);
								game_PlayerActions.clearButtonsAction(gui);
							}
						}
						title2.setText("Units in node " + units.size());
					}
				}
			}
			
			if(title3 != null){
				if(node == null){
					title3.setText("");
				}
				else{
					title3.setText("Recources type " + node.geology);
				}
			}
		}
	}

	private static void unitSelect(HashSet<Integer> units, GuiElementUnits table, GameData gamedata, int clientId, GuiElementIcon icon) {
		table.clear();
		table.setVisible(false);
		
		if(units != null){
			ArrayList<Unit> unitsList = new ArrayList<Unit>();			
			int counter = 0;
			int select = 0;
			boolean selected = false;
			
			for(Integer unitId: units){
				Unit unit = gamedata.units.getUnit(unitId);
				
				if(!selected && unit.playerId == clientId){
					select = counter;
					selected = true;
				}
				
				unitsList.add(unit);
				counter++;
			}
			
			table.add(unitsList);
			table.select(select);
			Unit unit = table.getSelectedUnit();
			Painter.addTask(new Task(Enums.Task.GUI_UNIT_SELECT, unit));
			
			table.setVisible(true);
		}
	}
}
