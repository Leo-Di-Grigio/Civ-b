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

	@Override
	public void run() {
		gameCycle.run();
	}
}
