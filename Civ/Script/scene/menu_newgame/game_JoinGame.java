package scene.menu_newgame;

import java.io.IOException;

import painter.Painter;
import misc.Enums;
import misc.Log;
import scene.game.scene_Game;
import scenedata.game.GameData;
import scenedata.game.GameMap;
import script.Script;
import tasks.Task;

public class game_JoinGame extends Script {

	// generate gameData use seed
	public static void sucess(String data) throws IOException {
		Log.debug("Execute game_JoinGame.sucess()");
		String [] info = data.split(":");
		
		long seed = Long.valueOf(info[0]);
		int mapSizeX = Integer.valueOf(info[1]);
		int mapSizeY = Integer.valueOf(info[2]);
	
		// prepare game map
		GameMap map = new GameMap(seed, mapSizeX, mapSizeY);
		
		// prepare GAME scene
		GameData gamedata = new GameData(map);
		scene_Game scene = new scene_Game(gamedata);
		
		// add scene in to Painter
		Painter.addScene(Enums.Scene.GAME, scene);
		Painter.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, Enums.Scene.PREPEARE));
	}

	// update 
	public static void failed(String data) {
		Log.debug("Execute game_JoinGame.failed()");
		Log.err("Connection failed " + data);
	}
}
