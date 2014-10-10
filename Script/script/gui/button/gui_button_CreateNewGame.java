package script.gui.button;

import painter.Painter;
import misc.Const;
import misc.Enums;
import misc.Log;
import scene.game.scene_SceneGame;
import scenedata.game.GameMap;
import scenedata.game.GameData;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_CreateNewGame extends ScriptGui {

	@Override
	public void execute(Task task){
		Log.debug("Execute gui_button_CreateNewGame");
		
		// prepare data
		GameMap map = new GameMap(Const.mapSizeX, Const.mapSizeY);
		GameData gamedata = new GameData(map);
		scene_SceneGame scene = new scene_SceneGame(gamedata);
		
		// add scene in to Painter
		Painter.addScene(Enums.Scene.GAME, scene);
		Painter.currentScene.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, "game"));
	}
}
