package scene.game;

import java.util.Vector;

import gui.GUI;
import gui.elements.GuiElementIcon;
import gui.elements.GuiElementPane;
import gui.elements.GuiElementTitle;
import painter.Painter;
import misc.Const;
import misc.Enums;
import misc.Environment;
import misc.Log;
import recources.Recources;
import scenedata.game.GameData;
import scenedata.game.Node;
import script.Script;
import tasks.Task;
import units.Unit;

public class game_SelectNode extends Script {

	public static void execute(GameData gamedata) {
		int nodeX = Environment.nodeSelectedX;
		int nodeY = Environment.nodeSelectedY;
		int mapX = Recources.getImage(Const.imgMinimap).getHeight(null);
		
		Log.debug("Execute game_SelectNode (" + nodeX + "," + nodeY + ")");

		if(nodeY >= 0 && nodeY < mapX){
			Node node = gamedata.map.nodes[nodeX][nodeY];
			Painter.currentScene.addTask(new Task(Enums.Task.GAME_SELECT_NODE, node));
		}
		else{
			Painter.currentScene.addTask(new Task(Enums.Task.GAME_SELECT_NODE, null));
		}
	}

	public static void execute(GUI gui, Node data) {
		GuiElementPane pane = (GuiElementPane)gui.get("infopane");
		
		if(pane != null){
			GuiElementIcon icon = (GuiElementIcon)pane.getElement("icon");
			GuiElementTitle title1 = (GuiElementTitle)pane.getElement("title-0");
			GuiElementTitle title2 = (GuiElementTitle)pane.getElement("title-1");
			GuiElementTitle title3 = (GuiElementTitle)pane.getElement("title-2");
			
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
					Vector<Unit> units = data.getAll();
					
					if(units == null){
						title2.setText("");
					}
					else{
						title2.setText("Units in node " + units.size());
					}
				}
			}
			
			if(title3 != null){
				if(data == null){
					title3.setText("");
				}
				else{
					title3.setText("Height in x: " + Environment.nodeSelectedX + " y: " + Environment.nodeSelectedY + " = " + data.height);
				}
			}
		}
	}
}
