package misc;

import gui.GUI;
import main.Config;
import painter.Painter;
import player.units.Unit;
import scene.game.unit_Interact;
import tasks.Task;

public class Environment {
	
	// mouse
	public static int mouseX;
	public static int mouseY;
	
	// frame size
	public static int frameSizeX;
	public static int frameSizeY;
	 
	// game camera
	public static int cameraX;
	public static int cameraY;
	
	// game node selected
	public static int nodeSelectedX;
	public static int nodeSelectedY;
	public static int nodeDrawCursorX;
	public static int nodeDrawCursorY;

	// game map
	public static int mapSizeX;
	public static int mapSizeY;
	
	public static void updateMousePosition(int x, int y){
		Environment.mouseX = x;
		Environment.mouseY = y;
		
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			if(Painter.currentSceneTitle == Enums.Scene.GAME){
				updateNodeSelectingNative();
			}
			return;
		}
		
		if(Config.renderMode == Enums.RenderMode.OPENGL){
			updateNodeSelectingOpenGL();
			return;
		}
	}
	
	private static void updateNodeSelectingNative(){
		int x = mouseX/32;
		int y = mouseY/32;
				
		if(x + cameraX < 0){
			nodeSelectedX = mapSizeX + x + cameraX;
		}
		else{
			nodeSelectedX = (x + cameraX)%mapSizeX;
		}
		
		nodeSelectedY = y + cameraY;
		
		nodeDrawCursorX = x;
		nodeDrawCursorY = y;
	}
	
	private static void updateNodeSelectingOpenGL(){
		// opengl
	}
	
	public static void updateFrameSize(int width, int height){
		Environment.frameSizeX = width;
		Environment.frameSizeY = height;
		
		Painter.addTask(new Task(Enums.Task.GUI_UPDATE_POSITION, null));
	}
	
	public static void moveCamera(GUI gui, Enums.Direct direct){
		unit_Interact.resetInteractMenu(gui);
		int h = Environment.frameSizeY/32;
		
		switch(direct){
			case UP: {
				if(cameraY - 1 >= 0){
					cameraY--;
				}
			} break;
			
			case DOWN: {
				if(cameraY + 1 < mapSizeX - h){
					cameraY++;
				}
			} break;
			
			case LEFT: {
				if(cameraX - 1 < 0){
					cameraX = mapSizeX - 1;
				}
				else{
					cameraX--;
				}
			} break;
			
			case RIGHT: {
				if(cameraX + 1 > mapSizeX - 1){
					cameraX = 0;
				}
				else{
					cameraX++;
				}
			} break;
		
			default: break;
		}
		
		Environment.updateMousePosition(mouseX, mouseY);
	}

	public static void moveCameraToUnit(Unit unit) {
		cameraX = unit.x - frameSizeX/64;
		cameraY = unit.y - frameSizeY/64;
		
		if(cameraX < 0){
			cameraX = 0;
		}
		else{
			if(cameraX >= mapSizeX){
				cameraX = mapSizeX - frameSizeX/32 - 1;
			}
		}
		
		if(cameraY < 0){
			cameraY = 0;
		}
		else{
			if(cameraX >= mapSizeY){
				cameraY = mapSizeY - frameSizeY/32 - 1;
			}
		}
	}
}
