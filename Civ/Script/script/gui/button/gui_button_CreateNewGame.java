package script.gui.button;

import gui.GUI;
import gui.elements.GuiElementGamesList;

import java.io.IOException;

import painter.Painter;
import misc.Const;
import misc.Enums;
import misc.Log;
import misc.TableLine;
import scene.game.scene_SceneGame;
import scenedata.game.GameMap;
import scenedata.game.GameData;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_CreateNewGame extends ScriptGui {

	@Override
	public void execute(Task task) throws IOException{
		Log.debug("Execute gui_button_CreateNewGame");
		
		try {
			GUI gui = (GUI)task.dataPost;
		
		
		if(gui != null){
			GuiElementGamesList list = (GuiElementGamesList)gui.get("gameslist");
			
			if(list != null){
				TableLine line = list.getSelectedLine();
				
				if(line != null){
					String id = line.getCell(0);
					
					if(id != null){
						Log.err("ID OF SELECTED GAME " + id);
					}
				}
			}
		}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
				
		// prepare data
		GameMap map = new GameMap(Const.mapSizeX, Const.mapSizeY);
		GameData gamedata = new GameData(map);
		scene_SceneGame scene = new scene_SceneGame(gamedata);
		
		// add scene in to Painter
		Painter.addScene(Enums.Scene.GAME, scene);
		Painter.currentScene.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, Enums.Scene.GAME));
	}
}
