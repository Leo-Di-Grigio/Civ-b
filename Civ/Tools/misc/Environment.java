package misc;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import gui.GUI;
import main.Config;
import painter.Painter;
import player.units.Unit;
import render.Render;
import scene.game.unit_Interact;
import tasks.Task;

public class Environment {
	
	// mouse
	public static int mouseX;
	public static int mouseY;
	
	// frame size
	public static boolean frameFullscreen;
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
	
	// GL tools
	public static float surfaceX;
	public static float surfaceY;
	public static float surfaceZ;
	
	private static FloatBuffer zBuffer;
	private static FloatBuffer coordinates;	 
	private static FloatBuffer modelview;
	private static FloatBuffer projection;
	private static IntBuffer viewport;
	private static int realY;
	
	// rotate
	public static float angle;
	
	public Environment(){
		zBuffer = FloatBuffer.allocate(1);
		coordinates = FloatBuffer.allocate(3);	 
		modelview = FloatBuffer.allocate(16);
		projection = FloatBuffer.allocate(16);
		viewport = IntBuffer.allocate(4);	
	}
	
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
			if(Painter.currentSceneTitle == Enums.Scene.GAME){
				updateNodeSelectingOpenGL();
			}
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
		GL2 gl = Render.getGL();
		GLU glu = Render.getGLU();
		
		gl.glGetFloatv(GL2.GL_MODELVIEW_MATRIX, modelview);
		gl.glGetFloatv(GL2.GL_PROJECTION_MATRIX, projection);
		gl.glGetIntegerv(GL2.GL_VIEWPORT, viewport);
	 
		realY = Render.getHeight() - mouseY - 1;
		 
		gl.glReadPixels(mouseX, realY, 1, 1, GL2.GL_DEPTH_COMPONENT, GL2.GL_FLOAT, zBuffer);
		glu.gluUnProject(mouseX, realY, zBuffer.get(0), modelview, projection, viewport, coordinates);
		
		surfaceX = coordinates.get(0);
		surfaceY = coordinates.get(1);
		surfaceZ = coordinates.get(2);
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
