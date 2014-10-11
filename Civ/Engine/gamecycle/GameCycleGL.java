package gamecycle;

import java.io.IOException;

import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import painter.Painter;
import render.Render;
import userapi.UserCanvasListener;
import userapi.UserKey;
import userapi.UserMotion;
import userapi.UserMouse;
import userapi.UserWheel;

import com.jogamp.opengl.util.FPSAnimator;

import main.Config;
import misc.Enums;
import misc.Environment;

public class GameCycleGL extends GameCycle implements GLEventListener {
	
	private GL3 gl;
	private GLCanvas canvas;
	private FPSAnimator animator;
	
	public GameCycleGL(JFrame frame) {
		super(Enums.RenderMode.OPENGL, frame);
	}
	
	@Override
	void initCycle() {
        GLProfile glProfile = GLProfile.get(GLProfile.GL3);
        GLCapabilities caps = new GLCapabilities(glProfile);
        canvas = new GLCanvas(caps);
        
        // init FPS pusher
        animator = new FPSAnimator(canvas, Config.fps);
        canvas.addGLEventListener(this);
        canvas.setSize(Config.frameWidth, Config.frameHeight);
        
        frame.getContentPane().add(canvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
        
		canvas.addMouseListener(new UserMouse());
		canvas.addMouseMotionListener(new UserMotion());
		canvas.addMouseWheelListener(new UserWheel());
		canvas.addKeyListener(new UserKey());
		canvas.addComponentListener(new UserCanvasListener());
		
        animator.start();
	}
	
	@Override
	public void display(GLAutoDrawable draw) {
		Environment.updateFrameSize(Render.getWidth(), Render.getHeight());
		
		gl = draw.getGL().getGL3();
		gl.glClear(GL3.GL_COLOR_BUFFER_BIT | GL3.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void init(GLAutoDrawable draw) {
		gl = draw.getGL().getGL3();
	}

	@Override
	public void reshape(GLAutoDrawable draw, int x, int y, int w, int h) {
		gl = draw.getGL().getGL3();
		
		gl.glViewport(x, y, w, h);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		
	}

	@Override
	void draw() throws IOException {
		Painter.draw(gl);
	}

	@Override
	public int getWidth() {
		return canvas.getWidth();
	}

	@Override
	public int getHeight() {
		return canvas.getHeight();
	}
}
