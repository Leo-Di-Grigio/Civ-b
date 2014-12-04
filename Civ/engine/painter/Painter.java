package painter;

import gui.tooltip.GuiTooltip;

import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.awt.TextRenderer;

import misc.Enums;
import misc.Log;
import scene.Scene;
import scene.choosegame.scene_ChooseGame;
import scene.connect.scene_Connect;
import scene.exit.scene_Exit;
import scene.load.scene_Load;
import scene.menu.scene_Menu;
import scene.settings.scene_Settings;
import tasks.Task;

public class Painter {
	
	private static HashMap<Enums.Scene, Scene> scenes;
	private static Scene currentScene;
	private static GuiTooltip tooltip;
	public static Enums.Scene currentSceneTitle;
	
	public Painter() {
		scenes = new HashMap<Enums.Scene, Scene>();
		
		scenes.put(Enums.Scene.MENU, new scene_Menu());
		scenes.put(Enums.Scene.CHOOSE_GAME, new scene_ChooseGame());
		scenes.put(Enums.Scene.LOAD, new scene_Load());
		scenes.put(Enums.Scene.SETTINGS, new scene_Settings());
		scenes.put(Enums.Scene.CONNECT, new scene_Connect());
		scenes.put(Enums.Scene.EXIT, new scene_Exit());
		
		currentScene = scenes.get(Enums.Scene.MENU);
		currentSceneTitle = Enums.Scene.MENU;
	}
	
	public static void addTask(Task task){
		currentScene.addTask(task);
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
		currentSceneTitle = scene;
		currentScene.addTask(new Task(Enums.Task.SCENE_LOADING, null));
	}
	
	public static void setTooltip(GuiTooltip tooltip){
		Painter.tooltip = tooltip;
	}
	
	// Native
	public static void draw(Graphics g, long tic) throws IOException{
		currentScene.update();
		currentScene.draw(g, tic);
		currentScene.drawGui(g, tic);
		Painter.drawTooltip(g, tic);
	}
	
	private static void drawTooltip(Graphics g, long tic){
		if(Painter.tooltip != null){
			Painter.tooltip.draw(g, tic);
		}
	}
	
	// GL
	public static void draw(GL2 gl, GLU glu, TextRenderer textrender) throws IOException{
		currentScene.update();
		currentScene.draw(gl, textrender);
		currentScene.drawGui(gl, textrender);
		Painter.drawTooltip(gl, textrender);
	}

	private static void drawTooltip(GL2 gl, TextRenderer textrender) {
		
	}
}
