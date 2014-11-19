package gamecycle;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import painter.Painter;
import recources.Recources;
import render.Render;
import shaders.ShaderMng;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextRenderer;

import main.Config;
import misc.Enums;
import misc.Environment;

public class GameCycleGL extends GameCycle implements GLEventListener {
	
	private GL2 gl;
	private GLU glu;
	
	private GLCanvas canvas;
	private FPSAnimator animator;
	
	private TextRenderer textrender;
	
	private float scaling; // scaling coeficent

	public GameCycleGL(JFrame frame) {
		super(Enums.RenderMode.OPENGL, frame);
	}
	
	@Override
	void initCycle() {
		GLProfile glProfile = GLProfile.get(GLProfile.GL2);
		GLCapabilities caps = new GLCapabilities(glProfile);
		canvas = new GLCanvas(caps);
	        
		// init FPS pusher	
		animator = new FPSAnimator(canvas, Config.fps);
		canvas.setSize(Config.frameWidth, Config.frameHeight);
	        
		frame.getContentPane().add(canvas);
		canvas.addGLEventListener(this);
		
		// not released
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (Config.glFrameFullscreen && device.isFullScreenSupported()) {
			frame.setUndecorated(true);
			frame.setResizable(false);
	        //frame.setIgnoreRepaint(true);
	        device.setFullScreenWindow(frame);
	        Environment.frameFullscreen = true;
		}
		else {
			frame.setSize(frame.getContentPane().getPreferredSize());
			frame.setResizable(true);
			Environment.frameFullscreen = false;
		}
		
		frame.setVisible(true);
		
		animator.start();
	}

	@Override
	public void init(GLAutoDrawable draw) {
		gl = draw.getGL().getGL2();
		glu = new GLU();
		
		draw.getAnimator().setUpdateFPSFrames(10, null);
		
		try {
			Recources.initGLRecources(gl, canvas);
			
			if(Config.glShaderLoad){
				new ShaderMng(gl);
			}
		} 
		catch (GLException | IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		new Painter();
		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
	        
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL2.GL_BLEND);
		
		textrender = new TextRenderer(new Font("ComicSans", Font.BOLD, 12));
		textrender.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		Environment.updateFrameSize(Render.getWidth(), Render.getHeight());
	}
	
	@Override
	public void display(GLAutoDrawable draw) {
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		try {
			draw();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		textrender.draw(draw.getAnimator().getLastFPS() + " FPS", 0, Environment.frameSizeY - 50);
		gl.glFlush();
	}
	
	@Override
	public void reshape(GLAutoDrawable draw, int x, int y, int w, int h) {
        gl = draw.getGL().getGL2();
        
        if (h <= 0){
            h = 1;
        }
        
        Environment.frameSizeX = w;
        Environment.frameSizeY = h;
        
        scaling = (float) w / (float) h;
        
        gl.glViewport(0, 0, w, h);
        
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, scaling, 0.1, 100.0);
        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
	}

	@Override
	public void dispose(GLAutoDrawable draw) {
		
	}

	@Override
	void draw() throws IOException {
		Painter.draw(gl, textrender);
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
		return gl;
	}
}
