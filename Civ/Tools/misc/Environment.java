package misc;

import gui.GUI;

import java.awt.Image;

import main.Config;
import painter.Painter;
import player.units.Unit;
import recources.Recources;
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
		
		Image map = Recources.getImage(Const.imgMinimap);
		if(map == null){
			return;
		}
		
		int sizeX = map.getWidth(null);
		
		int x = mouseX/32;
		int y = mouseY/32;
				
		if(x + cameraX < 0){
			nodeSelectedX = sizeX + x + cameraX;
		}
		else{
			nodeSelectedX = (x + cameraX)%sizeX;
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
		
		Image mapImage = Recources.getImage(Const.imgMinimap);
		int mapX = mapImage.getWidth(null);
		int h = Environment.frameSizeY/32;
		
		switch(direct){
			case UP: {
				if(cameraY - 1 >= 0){
					cameraY--;
				}
			} break;
			
			case DOWN: {
				if(cameraY + 1 < mapX - h){
					cameraY++;
				}
			} break;
			
			case LEFT: {
				if(cameraX - 1 < 0){
					cameraX = mapX - 1;
				}
				else{
					cameraX--;
				}
			} break;
			
			case RIGHT: {
				if(cameraX + 1 > mapX - 1){
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
		Image mapImage = Recources.getImage(Const.imgMinimap);
		int mapX = mapImage.getWidth(null);
		int mapY = mapImage.getHeight(null);
		

		cameraX = unit.x - frameSizeX/64;
		cameraY = unit.y - frameSizeY/64;
		
		if(cameraX < 0){
			cameraX = 0;
		}
		else{
			if(cameraX >= mapX){
				cameraX = mapX - frameSizeX/32 - 1;
			}
		}
		
		if(cameraY < 0){
			cameraY = 0;
		}
		else{
			if(cameraX >= mapY){
				cameraY = mapY - frameSizeY/32 - 1;
			}
		}
	}
}
