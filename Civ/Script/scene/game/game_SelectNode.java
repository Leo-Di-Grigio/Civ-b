package scene.game;

import java.util.HashSet;

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
import scenedata.game.GameData;
import scenedata.game.Node;
import script.Script;
import tasks.Task;

public class game_SelectNode extends Script {

	public static void selectNode(GameData gamedata) {
		int nodeX = Environment.nodeSelectedX;
		int nodeY = Environment.nodeSelectedY;
		int mapX = gamedata.map.sizeX;
		
		Log.debug("Execute game_SelectNode (" + nodeX + "," + nodeY + ")");

		if(nodeY >= 0 && nodeY < mapX){			
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
			
			if(icon != null){
				
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
					HashSet<Integer> units = node.getAll();
					
					if(units == null){
						if(unitSelectTable != null){
							unitSelect(null, unitSelectTable, gamedata, clientId);
							game_PlayerActions.clearButtonsAction(gui);
						}
						title2.setText("");
					}
					else{
						if(unitSelectTable != null){
							if(units.size() > 0){
								unitSelect(units, unitSelectTable, gamedata, clientId);
							}
							else{
								unitSelect(null, unitSelectTable, gamedata, clientId);
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

	private static void unitSelect(HashSet<Integer> units, GuiElementTable table, GameData gamedata, int clientId) {
		table.clear();
		
		if(units != null){
			table.setSize(table.getSizeX(), GuiElementTable.lineSize * units.size() + 15);
			table.setPosition(-5, -175 - table.getSizeY());
			
			for(Integer unitId: units){
				Unit unit = gamedata.units.getUnit(unitId);
				
				TableLine line = new TableLine(3);
				
				line.metadata = Enums.TableMetadata.UNIT;
				line.setCell(0, "" + unit.id);
				line.setCell(1, "playerId: " + unit.playerId);
				line.setCell(2, "unitType: " + unit.type);
				
				line.setHidden(0); // hide unitId
				table.add(line);				
			}
			
			table.select(0);
			table.setVisible(true);
		}
		else{
			table.setVisible(false);
		}
	}
}
