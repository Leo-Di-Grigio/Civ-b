package misc;

import java.awt.Image;
import main.Config;
import painter.Painter;
import recources.Recources;
import tasks.Task;

public class Environment {
	
	// mouse
	public static int mouseX;
	public static int mouseY;
	
	// frame size
	public static int width;
	public static int height;
	
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
			updateNodeSelectingNative();
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
			nodeSelectedX = Const.mapSizeX + x + cameraX;
		}
		else{
			nodeSelectedX = (x + cameraX)%Const.mapSizeX;
		}
		
		nodeSelectedY = y + cameraY;
		
		nodeDrawCursorX = x;
		nodeDrawCursorY = y;
	}
	
	private static void updateNodeSelectingOpenGL(){
		// opengl
	}
	
	public static void updateFrameSize(int width, int height){
		Environment.width = width;
		Environment.height = height;
		
		Painter.currentScene.addTask(new Task(Enums.Task.GUI_UPDATE_POSITION, null));
	}
	
	public static void moveCamera(Enums.Direct direct){
		Image mapImage = Recources.getImage("minimap_height");
		int mapX = mapImage.getWidth(null);
		int h = Environment.height/32;
		
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
}
