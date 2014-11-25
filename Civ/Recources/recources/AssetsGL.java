package recources;

import java.awt.Cursor;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.media.opengl.GL2;
import javax.media.opengl.GLException;
import javax.media.opengl.awt.GLCanvas;

import shaders.Shader;
import shaders.ShaderMng;
import userapi.UserCanvasListener;
import userapi.UserKey;
import userapi.UserMotion;
import userapi.UserMouse;
import userapi.UserWheel;

import com.jogamp.graph.font.Font;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

import engine.Engine;
import main.Config;
import misc.Const;
import misc.Log;

public class AssetsGL {
	
	private static Vector<Texture> tex;
	private static HashMap<String, Integer> indexes;
	private static HashMap<String, Cursor> cursors;
	private static Font font;
	
	private static int [] terrain;
	
	private static ShaderMng shaders;
	
	private GL2 gl;
	
	public AssetsGL() {
		super();
		
		indexes = new HashMap<String, Integer>();
		cursors = new HashMap<String, Cursor>();
		tex = new Vector<Texture>();
	}

	public void init(GL2 gl, GLCanvas canvas) throws GLException, IOException, FontFormatException {
		this.gl = gl;
		gl.glEnable(GL2.GL_TEXTURE_2D);
		
		loadGui();
		loadGreyTiles();
		loadGeologyTiles();
		loadTerrain();
		loadCursors();
		loadUnits();
		loadActions();
		loadFont();
		loadItems();
		
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
	
	private void loadGui() throws GLException, IOException {
		// GUI ELEMENTS
		initTexture(Const.imgNull, Const.assetsGL + "gui/null.png");
		initTexture(Const.imgVoid, Const.assetsGL + "gui/void.png");
		initTexture(Const.imgNullSelected, Const.assetsGL + "gui/null_selected.png");
		
		// menu
		initTexture(Const.imgMenu, Const.assetsGL + "menu.png");
		
		// buttons
		initTexture(Const.imgButton, Const.assetsGL + "gui/button.png");
		initTexture(Const.imgButtonSelected, Const.assetsGL + "gui/button_select.png");
		initTexture(Const.imgButtonMenu, Const.assetsGL + "gui/button_menu.png");
		initTexture(Const.imgButtonMenuSelect, Const.assetsGL + "gui/button_menu_select.png");
		
		// pane
		initTexture(Const.imgPane, Const.assetsGL + "gui/pane.png");
		
		// cursor
		initTexture(Const.imgSelect, Const.assetsGL + "cursor/cursor_nodeselect_draw.png");

		// window
		initTexture(Const.imgWindow, Const.assetsGL + "gui/window.png");
		
		// chat
		initTexture(Const.imgChat, Const.assetsGL + "gui/chat.png");
		initTexture(Const.imgChatSelected, Const.assetsGL + "gui/chat_selected.png");
		
		// inventory
		initTexture(Const.imgInventorySlot, Const.assetsGL + "gui/inventory_slot.png");
		
		// tooltip
		initTexture(Const.imgToolTip, Const.assetsGL + "gui/tooltip_background.png");
		
		// tech
		initTexture(Const.imgTechUnlearn, Const.assetsGL + "gui/tech_unlearn.png");
		initTexture(Const.imgTechLearn, Const.assetsGL + "gui/tech_learn.png");
		initTexture(Const.imgTechAvaible, Const.assetsGL + "gui/tech_avaible.png");
	}

	private void loadItems() throws GLException, IOException {
		initTexture(Const.imgItemRecource, Const.assetsGL + "items/recource.png");
		initTexture(Const.imgItemCampPack, Const.assetsGL + "items/camppack.png");
	}

	private void loadActions() throws GLException, IOException {
		// actions
		initTexture(Const.imgActionMoveto, Const.assetsGL + "actions/action_moveto.png");
		initTexture(Const.imgActionCityBuild, Const.assetsGL + "actions/action_citybuild.png");
		initTexture(Const.imgActionBuildRecruit, Const.assetsGL + "actions/action_buildrecruit.png");
		initTexture(Const.imgActionMine, Const.assetsGL + "actions/action_mine.png");
		initTexture(Const.imgActionInventory, Const.assetsGL + "actions/action_inventory.png");
		initTexture(Const.imgActionInteract, Const.assetsGL + "actions/action_interact.png");
		
		// interact actions
		initTexture(Const.imgInteractAttack, Const.assetsGL + "actions/interact_attack.png");
		initTexture(Const.imgInteractTalk, Const.assetsGL + "actions/interact_talk.png");
		initTexture(Const.imgInteractWorkAt, Const.assetsGL + "actions/interact_workat.png");
		initTexture(Const.imgInteractRepair, Const.assetsGL + "actions/interact_repair.png");
		initTexture(Const.imgInteractBuildUpdate, Const.assetsGL + "actions/interact_build.png");
		initTexture(Const.imgInteractBuildCityBuilding, Const.assetsGL + "actions/interact_build.png");
	
	}

	private void loadUnits() throws GLException, IOException {
		initTexture(Const.imgUnitPlayerAtlas, Const.assetsGL + "units/playerColor.png");
		initTexture(Const.imgUnitAvatar, Const.assetsGL + "units/avatar.png");
		initTexture(Const.imgUnitRecruit, Const.assetsGL + "units/unit_recruit.png");
		initTexture(Const.imgUnitCity, Const.assetsGL + "units/unit_city.png");	
	}

	private void loadCursors() {
		
	}

	private void loadTerrain() throws GLException, IOException {
		initTexture(Const.imgTerrainWater, Const.assetsGL + "terrain/water.png");
		initTexture(Const.imgTerrainWaterBorder, Const.assetsGL + "terrain/waterBorder.png");
		initTexture(Const.imgTerrainLand, Const.assetsGL + "terrain/land.png");
		
		terrain = new int[2];
		gl.glGenTextures(terrain.length, terrain, 0);
		
		genTexture(Const.assetsGL + "terrain/water.png", 0);
        genTexture(Const.assetsGL + "terrain/land.png" , 1);
	}
	
	private void genTexture(String path, int index){
		BufferedImage image = null;
	    
	    try {
	    	File img = new File(Config.classPath + path);
		    image = ImageIO.read(img);
		    
		    int size = image.getHeight() * image.getWidth() * 3;
		    ByteBuffer buffer = ByteBuffer.allocate(size);
		    
		    int rgb = 0;
		    for(int i = 0; i < image.getWidth(); ++i){
		    	for(int j = 0; j < image.getHeight(); ++j){
		    		rgb = image.getRGB(i, j);
		    		
		    		buffer.put((byte) ((rgb >> 16) & 0xFF)); // red
		    		buffer.put((byte) ((rgb >> 8) & 0xFF));  // green
                    buffer.put((byte) (rgb & 0xFF));         // blue
		    	}
		    }
		    buffer.flip();
		    
		    // load tex into gpu
		    gl.glBindTexture(GL2.GL_TEXTURE_2D, terrain[index]);
		    gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGB, image.getWidth(), image.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, buffer);
		    
		    // setup tex
	        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
	        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
	        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_CLAMP_TO_EDGE); 
	        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_CLAMP_TO_EDGE);
	    } 
	    catch (IOException e){
	    	Log.err("Cant read file " + Config.classPath + path);
	        e.printStackTrace();
	    }
	}

	private void loadGeologyTiles() {
		int rgb = 0;
		
		for(int i = 0; i < 128; ++i){
			BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
			
			if(i % 4 == 0)
				rgb = (int)i*16 << 16 + (int)i << 8 + (int)i;
			if(i % 4 == 1)
				rgb = (int)i << 16 + (int)i*16 << 8 + (int)i;
			if(i % 4 == 2)
				rgb = (int)i << 16 + (int)i << 8 + (int)i*16;
			if(i % 4 == 3)
				rgb = (int)i*16 << 16 + (int)i*16 << 8 + (int)i*16;
			
			for(int x = 0; x < 32; ++x){
				for(int y = 0; y < 32; ++y){
					img.setRGB(x, y, rgb); 
				}
			}
			
			initTexture("geology"+i, img);
		}
	}

	private void loadGreyTiles() {
		int rgb = 0;
		
		for(int i = 0; i < 16; ++i){
			BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
			
			if(i == 0){
				rgb = 0x0000FF;
			}
			else{
				rgb = (i*16 << 16)  + (i*16 << 8) + i*16;
			}
			
			for(int x = 0; x < 32; ++x){
				for(int y = 0; y < 32; ++y){
					img.setRGB(x, y, rgb); 
				}
			}
			
			initTexture("grey"+i, img);
		}
	}

	public void loadTemperatureColor(int tMin, int tMax) {
		int tic = Math.abs(tMax - tMin + 5) / 5;
		int colorTic = 255/tic;
		
		int t = tMin;
		int r = 255;
		int g = 0;
		int b = 0;
		
		// r,0,b -> 0,0,b
		for(int i = 0; i < tic; ++i){
			r -= colorTic;
			setColor(r, g, b, t++);
		}
		
		// 0,0,b -> 0,g,b
		for(int i = 0; i < tic; ++i){
			g += colorTic;
			setColor(r, g, b, t++);
		}
		
		// 0,g,b -> 0,g,0
		for(int i = 0; i < tic; ++i){
			b -= colorTic;
			setColor(r, g, b, t++);
		}
		
		// 0,g,0 -> r,g,0
		for(int i = 0; i < tic; ++i){
			r += colorTic;
			setColor(r, g, b, t++);
		}
		// r,g,0 -> r,0,0
		for(int i = 0; i < tic; ++i){
			g -= colorTic;
			setColor(r, g, b, t++);
		}
	}
	
	private void setColor(int r, int g, int b, int t){
		BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		int a = 48;
		int col = (a << 24) + (r << 16) + (g << 8) + b;
		
		for(int x = 0; x < 32; ++x){
			for(int y = 0; y < 32; ++y){
				img.setRGB(x, y, col);
			}
		}
		
		initTexture("temp"+t, img);
	}
	
	public void setCursor(String name) {
		if(Config.os != "Linux"){
			if(cursors.containsKey(name)){
				Engine.frame.getContentPane().setCursor(cursors.get(name));
			}
		}		
	}

	private void loadFont() throws FontFormatException, IOException {
		
	}
	
	public Font getFont() {
		return font;
	}
	
	public static void loadSahders(GL2 gl) throws IOException {
		shaders = new ShaderMng(gl);
	}
	
	public static Shader getShader(String key){
		return shaders.getShader(key);
	}
	
	protected void initTexture(String texName, BufferedImage img){		
		Texture t = AWTTextureIO.newTexture(gl.getGLProfile(), img, true);
		initTexture(texName, t);
	}
	
	private void initTexture(String texName, String path) throws GLException, IOException{
		Texture t = TextureIO.newTexture(new File(path), false);
		initTexture(texName, t);
	}
	
	private void initTexture(String texName, Texture t){
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
		Texture t = tex.get(indexes.get(texKey));
		t.bind(gl);
		t.enable(gl);
	}

	public void disableTexure(GL2 gl, String texKey) {
		tex.get(indexes.get(texKey)).disable(gl);
	}

	public Texture getTexture(String texKey) {
		return tex.get(indexes.get(texKey));
	}
	
	public void bindMultiTexture(GL2 gl, String [] texNames){
        // enable
        gl.glEnable(GL2.GL_TEXTURE_2D);
        
        // water
        gl.glActiveTexture(GL2.GL_TEXTURE0);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, terrain[0]);

        // land
        gl.glActiveTexture(GL2.GL_TEXTURE1);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, terrain[1]);
	}
	
	public void disableMultiTexture(GL2 gl){
		gl.glDisable(GL2.GL_TEXTURE_2D);
		gl.glActiveTexture(GL2.GL_TEXTURE0);
	}
	
	public int getTextureId(String texKey) {
		return indexes.get(texKey);
	}
}
