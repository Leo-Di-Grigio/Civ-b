package recources;

import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;
import javax.media.opengl.awt.GLCanvas;

import userapi.UserCanvasListener;
import userapi.UserKey;
import userapi.UserMotion;
import userapi.UserMouse;
import userapi.UserWheel;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import misc.Const;
import misc.Log;

public class AssetsGL {
	
	private static Vector<Texture> tex;
	private static HashMap<String, Integer> indexes;
	private static HashMap<String, Cursor> cursors;
	
	public AssetsGL() {
		super();
		
		indexes = new HashMap<String, Integer>();
		cursors = new HashMap<String, Cursor>();
		tex = new Vector<Texture>();
	}

	public void init(GL2 gl, GLCanvas canvas) throws GLException, IOException {
		gl.glEnable(GL2.GL_TEXTURE_2D);
		
		loadGui(gl);
		loadGreyTiles(gl);
		loadGeologyTiles(gl);
		loadTerrain(gl);
		loadCursors(gl);
		loadUnits(gl);
		loadActions(gl);
		loadFont(gl);
		loadItems(gl);
		
		System.gc();
		Log.debug("Assets GL textures loaded: " + indexes.size());
		Log.debug("Assets GL cursors loaded: " + cursors.size());
		
		// events inited here, because for texture initing need GL context :( bullcode
		canvas.addMouseListener(new UserMouse());
		canvas.addMouseMotionListener(new UserMotion());
		canvas.addMouseWheelListener(new UserWheel());
		canvas.addKeyListener(new UserKey());
		canvas.addComponentListener(new UserCanvasListener());
	}
	
	private void loadGui(GL2 gl) throws GLException, IOException {
		// GUI ELEMENTS
		initTexture(Const.imgNull, Const.assetsGL + "gui/null.png", gl);
		initTexture(Const.imgVoid, Const.assetsGL + "gui/void.png", gl);
		initTexture(Const.imgNullSelected, Const.assetsGL + "gui/null_selected.png", gl);
		
		// menu
		initTexture(Const.imgMenu, Const.assetsGL + "menu.png", gl);
		
		// buttons
		initTexture(Const.imgButton, Const.assetsGL + "gui/button.png", gl);
		initTexture(Const.imgButtonSelected, Const.assetsGL + "gui/button_select.png", gl);
		initTexture(Const.imgButtonMenu, Const.assetsGL + "gui/button_menu.png", gl);
		initTexture(Const.imgButtonMenuSelect, Const.assetsGL + "gui/button_menu_select.png", gl);
		
		// pane
		initTexture(Const.imgPane,  Const.assetsGL + "gui/pane.png", gl);
	}

	private void loadItems(GL2 gl) {
		
	}

	private void loadFont(GL2 gl) {
		
	}

	private void loadActions(GL2 gl) {
		
	}

	private void loadUnits(GL2 gl) {
		
	}

	private void loadCursors(GL2 gl) {
		
	}

	private void loadTerrain(GL2 gl) {
		
	}

	private void loadGeologyTiles(GL2 gl) {
		
	}

	private void loadGreyTiles(GL2 gl) {
		
	}

	public void loadTemperatureColor(int tMin, int tMax) {
		
	}
	
	public void setCursor(String name) {
		
	}

	public Font getFont() {
		return null;
	}
	
	private void initTexture(String texName, String path, GL2 gl) throws GLException, IOException{
		Log.debug("Load " + texName);
		Texture t = TextureIO.newTexture(new File(path), false);
		t.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR); 
		t.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR); 
		t.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, GL2.GL_CLAMP_TO_EDGE); 
		t.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, GL2.GL_CLAMP_TO_EDGE);
		t.disable(gl);
		
		int index = tex.size();
		indexes.put(texName, index);
		tex.add(t);
	}

	public void bindTexure(GL2 gl, String texKey) {
		tex.get(indexes.get(texKey)).bind(gl);
		tex.get(indexes.get(texKey)).enable(gl);
	}

	public void disableTexure(GL2 gl, String texKey) {
		tex.get(indexes.get(texKey)).disable(gl);
	}

	public Texture getTexture(String texKey) {
		return tex.get(indexes.get(texKey));
	}
}
