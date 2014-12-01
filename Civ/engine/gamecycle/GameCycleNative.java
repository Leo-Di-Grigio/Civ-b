package gamecycle;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import painter.Painter;
import recources.Recources;
import render.Render;
import userapi.UserCanvasListener;
import userapi.UserKey;
import userapi.UserMotion;
import userapi.UserMouse;
import userapi.UserWheel;
import main.Config;
import misc.Enums;
import misc.Environment;

public class GameCycleNative extends GameCycle {
	
	private Graphics2D g;
	private Canvas canvas;

	//  cycle FPS varibales
	protected static long cycleTime;
	protected static long lastTime;
	protected static long elapsedTime;
	protected static long lastUpdate;
	
	// Frame number
	protected static long tic;
		
	public GameCycleNative(JFrame frame) {
		super(Enums.RenderMode.NATIVE, frame);
	}

	@Override
	void initCycle() {
		canvas = new Canvas();
		frame.getContentPane().add(canvas);
		frame.setVisible(true);
		
		new Painter();
		
		canvas.addMouseListener(new UserMouse());
		canvas.addMouseMotionListener(new UserMotion());
		canvas.addMouseWheelListener(new UserWheel());
		canvas.addKeyListener(new UserKey());
		canvas.addComponentListener(new UserCanvasListener());
	}
	
	@Override
	public void run(){
		// update
		Environment.updateFrameSize(Render.getWidth(), Render.getHeight());
		
		// begin
		canvas.createBufferStrategy(2);
		BufferStrategy strategy = canvas.getBufferStrategy();
		
		cycleTime = System.currentTimeMillis();
		lastTime = System.currentTimeMillis();
		
		canvas.setFont(Recources.getFont());
		
		try {
			while(true){			
				cycle(strategy);
				syncFrame();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void cycle(BufferStrategy strategy) throws IOException{
		g = (Graphics2D)strategy.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.black);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		draw();
		
		g.dispose();
		strategy.show();
		
		lastUpdate = System.currentTimeMillis();
	}
	
	private void syncFrame(){
		cycleTime = cycleTime + Config.frameRate;
		long delta = cycleTime - System.currentTimeMillis();
		
		try { 
			Thread.sleep(Math.max(0, delta));
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		elapsedTime = System.currentTimeMillis() - lastTime;
	}
	
	@Override
	void draw() throws IOException {
		Painter.draw(g, tic++);
	}

	@Override
	public int getWidth() {
		return canvas.getWidth();
	}

	@Override
	public int getHeight() {
		return canvas.getHeight();
	}

	@Override
	public GL2 getGL() {
		return null;
	}

	@Override
	public GLU getGLU() {
		return null;
	}
}
