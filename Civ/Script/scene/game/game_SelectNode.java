package scene.game;

import java.util.HashSet;

import database.DB;
import gui.GUI;
import gui.elements.GuiElementIcon;
import gui.elements.GuiElementPane;
import gui.elements.GuiElementTable;
import gui.elements.GuiElementTitle;
import gui.misc.TableLine;
import painter.Painter;
import player.units.Unit;
import misc.Enums;
import misc.Environment;
import misc.Log;
import recources.Recources;
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
		
		if(pane != null){
			GuiElementIcon icon = (GuiElementIcon)pane.getElement(scenegui_Game.uiInfopaneIcon);
			GuiElementTitle title1 = (GuiElementTitle)pane.getElement(scenegui_Game.uiInfopaneTitle0);
			GuiElementTitle title2 = (GuiElementTitle)pane.getElement(scenegui_Game.uiInfopaneTitle1);
			GuiElementTitle title3 = (GuiElementTitle)pane.getElement(scenegui_Game.uiInfopaneTitle2);
			GuiElementTable unitSelectTable = (GuiElementTable)gui.get(scenegui_Game.uiUnitSelect);
			
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

	private static void unitSelect(HashSet<Integer> units, GuiElementTable table, GameData gamedata, int clientId, GuiElementIcon icon) {
		table.clear();
		
		if(units != null){
			table.setSize(table.getSizeX(), GuiElementTable.lineSize * units.size() + 15);
			table.setPosition(-5, -175 - table.getSizeY());
			
			int counter = 0;
			int toSelect = 0;
			boolean selected = false;
			
			for(Integer unitId: units){
				Unit unit = gamedata.units.getUnit(unitId);
				
				TableLine line = new TableLine(5);
				
				line.metadata = Enums.TableMetadata.UNIT;
				
				// hidden data
				line.setCell(0, "" + unit.id);
				line.setCell(1, "" + unit.playerId);
				line.setCell(2, "" + unit.type);
				
				// showed data
				line.setCell(3, gamedata.users.players.get(unit.playerId).name);
				line.setCell(4, DB.getUnitTitle(unit.type));
				
				// set hidden cells
				line.setHidden(0); // hide unitId
				line.setHidden(1); // hide playerId
				line.setHidden(2); // hide unit.type
				table.add(line);
				
				// select first player unit
				if(!selected && unit.playerId == gamedata.clientId){
					toSelect = counter;
					selected = true;
				}
				counter++;
			}
			
			table.select(toSelect);
			
			if(icon != null){
				int selectedUnitType = Integer.parseInt(table.getSelectedLine().getCell(2)); // get unit.type
				icon.setTexture(Recources.getUnitImage(selectedUnitType));
			}
			
			table.setVisible(true);
		}
		else{
			table.setVisible(false);
		}
	}
}
