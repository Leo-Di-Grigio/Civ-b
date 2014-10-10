package gamecycle;

import javax.swing.JFrame;

import painter.Painter;
import misc.Enums;
import misc.Log;

public abstract class GameCycle extends Thread {
	
	protected JFrame frame;
	
	public GameCycle(Enums.RenderMode mode, JFrame frame) {
		super();
		
		this.frame = frame;
		new Painter();
		
		initCycle();
		Log.msg("Load GameCycle " + mode);
	}
	
	abstract void initCycle();
	abstract void draw();

	abstract public int getWidth();
	abstract public int getHeight();
}
