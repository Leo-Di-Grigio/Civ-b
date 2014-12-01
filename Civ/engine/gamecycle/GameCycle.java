package gamecycle;

import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import misc.Enums;
import misc.Log;

public abstract class GameCycle extends Thread {
	
	protected JFrame frame;
	
	public GameCycle(Enums.RenderMode mode, JFrame frame) {
		super();
		this.frame = frame;
		
		initCycle();
		
		Log.msg("Load GameCycle " + mode);
	}
	
	abstract void initCycle();
	abstract void draw() throws IOException;

	abstract public int getWidth();
	abstract public int getHeight();
	
	abstract public GL2 getGL();
	abstract public GLU getGLU();
}
