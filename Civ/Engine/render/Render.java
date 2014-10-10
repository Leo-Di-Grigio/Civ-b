package render;

import javax.swing.JFrame;
import gamecycle.GameCycle;
import gamecycle.GameCycleGL;
import gamecycle.GameCycleNative;
import misc.Enums;
import misc.Log;

public class Render implements Runnable {
	
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
	}
	
	public static int getWidth(){
		return gameCycle.getWidth();
	}
	
	public static int getHeight(){
		return gameCycle.getHeight();
	}

	@Override
	public void run() {
		gameCycle.run();
	}
}
