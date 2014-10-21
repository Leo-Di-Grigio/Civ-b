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
import player.units.UnitsMng;
import misc.Const;
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
		int mapX = Recources.getImage(Const.imgMinimap).getHeight(null);
		
		Log.debug("Execute game_SelectNode (" + nodeX + "," + nodeY + ")");

		if(nodeY >= 0 && nodeY < mapX){
			Node node = gamedata.map.map[nodeX][nodeY];
			Painter.addTask(new Task(Enums.Task.GAME_SELECT_NODE, node));
		}
		else{
			Painter.addTask(new Task(Enums.Task.GAME_SELECT_NODE, null));
		}
	}

	public static void updateGuiData(GUI gui, Node data) {
		GuiElementPane pane = (GuiElementPane)gui.get(scenegui_Game.uiInfopane);
		
		if(pane != null){
			GuiElementIcon icon = (GuiElementIcon)pane.getElement(scenegui_Game.uiInfopaneIcon);
			GuiElementTitle title1 = (GuiElementTitle)pane.getElement(scenegui_Game.uiInfopaneTitle0);
			GuiElementTitle title2 = (GuiElementTitle)pane.getElement(scenegui_Game.uiInfopaneTitle1);
			GuiElementTitle title3 = (GuiElementTitle)pane.getElement(scenegui_Game.uiInfopaneTitle2);
			GuiElementTable unitSelectTable = (GuiElementTable)gui.get(scenegui_Game.uiUnitSelect);
			
			if(icon != null){
				
			}
			
			// Node map info
			if(title1 != null){
				if(data == null){
					title1.setText("");
				}
				else{
					title1.setText("Height in x: " + Environment.nodeSelectedX + " y: " + Environment.nodeSelectedY + " = " + data.height);
				}
			}
			
			// Node units
			if(title2 != null){
				if(data == null){
					title2.setText("");
				}
				else{
					HashSet<Integer> units = data.getAll();
					
					if(units == null){
						if(unitSelectTable != null){
							unitSelectTable.setVisible(false);
							unitSelect(null, unitSelectTable);
						}
						title2.setText("");
					}
					else{
						if(unitSelectTable != null){
							if(units.size() > 0){
								unitSelectTable.setVisible(true);
								unitSelect(units, unitSelectTable);
							}
							else{
								unitSelectTable.setVisible(false);
								unitSelect(null, unitSelectTable);
							}
						}
						title2.setText("Units in node " + units.size());
					}
				}
			}
			
			if(title3 != null){
				if(data == null){
					title3.setText("");
				}
				else{
					title3.setText("Recources type " + data.geology);
				}
			}
		}
	}

	private static void unitSelect(HashSet<Integer> units, GuiElementTable table) {
		table.clear();
		
		if(units != null){
			table.setSize(table.getSizeX(), GuiElementTable.lineSize * units.size() + 15);
			table.setPosition(-5, -175 - table.getSizeY());
			
			for(Integer unitId: units){
				Unit unit = UnitsMng.getUnit(unitId);
				TableLine line = new TableLine(3);
				
				line.setCell(0, "unitId: " + unit.id);
				line.setCell(1, "unitType: " + unit.type);
				line.setCell(2, "playerId: " + unit.playerId);
				table.add(line);
			}
			
			table.select(0);
		}
	}
}
