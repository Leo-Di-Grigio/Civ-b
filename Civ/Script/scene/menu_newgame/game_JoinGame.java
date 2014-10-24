package scene.menu_newgame;

import java.io.IOException;

import painter.Painter;
import misc.Enums;
import misc.Log;
import scene.game.scene_Game;
import scene.prepare.scene_Prepare;
import scenedata.game.GameData;
import script.Script;
import tasks.Task;

public class game_JoinGame extends Script {

	// generate gameData use seed
	public static void sucess(String data) throws IOException {
		Log.debug("Execute game_JoinGame.sucess()");
		String [] info = data.split(":");
		
		long seed = Long.parseLong(info[0]);
		int mapSizeX = Integer.parseInt(info[1]);
		int mapSizeY = Integer.parseInt(info[2]);
		int playerId = Integer.parseInt(info[3]); // self ID for correct unit selecting and contol
		
		// prepare GAME scene
		GameData gamedata = new GameData(playerId, seed, mapSizeX, mapSizeY);
		
		scene_Prepare prepeare = new scene_Prepare(gamedata);
		scene_Game scene = new scene_Game(gamedata);
		
		// add scene in to Painter
		Painter.addScene(Enums.Scene.PREPEARE, prepeare);
		Painter.addScene(Enums.Scene.GAME, scene);
		Painter.addTask(new Task(Enums.Task.PAINTER_CHANGE_SCENE, Enums.Scene.PREPEARE));
	}

	// update 
	public static void failed(String data) {
		Log.debug("Execute game_JoinGame.failed()");
		Log.err("Connection failed " + data);
	}
}
