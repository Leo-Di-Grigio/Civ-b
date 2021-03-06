package render;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import gamecycle.GameCycle;
import gamecycle.GameCycleGL;
import gamecycle.GameCycleNative;
import main.Config;
import misc.Enums;
import misc.Log;

public class Render {
	
	protected static GameCycle gameCycle;
	
	public Render(Enums.RenderMode mode, JFrame frame) {		
		switch(mode){
			case NATIVE:
				gameCycle = new GameCycleNative(frame);
				break;
				
			case OPENGL:
				gameCycle = new GameCycleGL(frame);
				break;
				
			default:
				Log.err("Game cycle is not found");
				System.exit(0);
				break;
		}
		
		new Thread(gameCycle).start();
	}
	
	public static int getWidth(){
		if(gameCycle != null){
			return gameCycle.getWidth();
		}
		else{
			return 0;
		}
	}
	
	public static int getHeight(){
		if(gameCycle != null){
			return gameCycle.getHeight();
		}
		else{
			return 0;
		}
	}
	
	public static GL2 getGL(){
		if(Config.renderMode == Enums.RenderMode.OPENGL){
			return gameCycle.getGL();
		}
		else{
			return null;
		}
	}
	
	public static GLU getGLU(){
		if(Config.renderMode == Enums.RenderMode.OPENGL){
			return gameCycle.getGLU();
		}
		else{
			return null;
		}
	}
}
