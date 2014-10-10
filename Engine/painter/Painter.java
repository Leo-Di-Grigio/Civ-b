package painter;

import java.awt.Graphics;
import java.util.HashMap;

import javax.media.opengl.GL3;

import misc.Enums;
import misc.Log;
import scene.Scene;
import scene.menu.scene_SceneMenu;
import scene.menu_exit.scene_SceneMenuExit;
import scene.menu_loadgame.scene_SceneMenuLoadGame;
import scene.menu_newgame.scene_SceneMenuNewGame;
import scene.menu_settings.scene_SceneMenuSettings;
import tasks.Task;

public class Painter {
	
	private static HashMap<Enums.Scene, Scene> scenes;
	public static Scene currentScene;
	
	public Painter() {
		scenes = new HashMap<Enums.Scene, Scene>();
		
		scenes.put(Enums.Scene.MENU, new scene_SceneMenu());
		scenes.put(Enums.Scene.MENU_NEWGAME, new scene_SceneMenuNewGame());
		scenes.put(Enums.Scene.MENU_LOADGAME, new scene_SceneMenuLoadGame());
		scenes.put(Enums.Scene.MENU_SETTINGS, new scene_SceneMenuSettings());
		scenes.put(Enums.Scene.MENU_EXIT, new scene_SceneMenuExit());
		
		currentScene = scenes.get(Enums.Scene.MENU);
	}
	
	public static void addScene(Enums.Scene type, Scene scene){
		Log.debug("Add scene " + type);
		scenes.put(type, scene);
	}
	
	public static void removeScene(Enums.Scene type){
		if(scenes.containsKey(type)){
			Log.debug("Remove scene " + type);
			scenes.remove(type);
		}
	}
	
	public static void switchScene(Enums.Scene scene){
		currentScene.clearTasksPool();
		currentScene = scenes.get(scene);
		currentScene.addTask(new Task(Enums.Task.SCENE_LOADING, null));
	}
	
	public static void draw(Graphics g){
		currentScene.update();
		currentScene.draw(g);
		currentScene.drawGui(g);
	}
	
	public static void draw(GL3 gl){
		currentScene.update();
		currentScene.draw(gl);
		currentScene.drawGui(gl);
	}
}
