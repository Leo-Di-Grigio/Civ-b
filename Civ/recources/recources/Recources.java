package recources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.texture.Texture;

import database.DB;
import recources.nongl.Tile;
import shaders.Shader;
import main.Config;
import misc.Const;
import misc.Enums;
import misc.Log;

public class Recources {
	
	private static AssetsNative assetsNative;
	private static AssetsGL assetsGL;
	
	public Recources(Enums.RenderMode mode) throws FontFormatException, IOException{
		Log.msg("Recources loaded " + mode);
		
		switch(mode){
			case NATIVE:
				assetsNative = new AssetsNative();
				assetsNative.init();
				break;
			
			case OPENGL:
				assetsGL = new AssetsGL();
				// assetsGL inited after GL context (GameCycleGL.java) initiation
				break;
			
			default:
				assetsNative = null;
				Log.err("Assets is not avaible");
				System.exit(0);
				break;
		}
	}
	
	public static Image getImage(String name){
		return assetsNative.getImage(name);
	}
	
	public static void addImage(String name, Tile tile){
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			assetsNative.addImage(name, tile);
		}
		else{
			BufferedImage img = (BufferedImage)tile.getImage();
			assetsGL.initTexture(name, img);
		}
	}

	public static void setCursor(String name) {
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			assetsNative.setCursor(name);
		}
		else{
			assetsGL.setCursor(name);
		}
	}

	public static Font getFont() {
		return assetsNative.getFont();
	}

	public static Image getUnitImage(int unitCode) {
		Image img = getImage(Const.imgNull);
		
		switch(unitCode){
			case DB.unitAvatar:
				return getImage(Const.imgUnitAvatar);
				
			case DB.buildingQuarter:
				return getImage(Const.imgUnitCity);
				
			case DB.unitNovice:
				return getImage(Const.imgUnitRecruit);
		}
		
		return img;
	}

	public static void loadTemperatureColor(int tMin, int tMax) {
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			assetsNative.loadTemperatureColor(tMin, tMax);
		}
		else{
			assetsGL.loadTemperatureColor(tMin, tMax);
		}
	}

	public static Image getImage(int itemIcon) {
		return assetsNative.getItem(itemIcon);
	}
	
	// GL
	public static void initGLRecources(GL2 gl, GLCanvas canvas) throws GLException, IOException, FontFormatException{
		assetsGL.init(gl, canvas);
	}
	
	public static void bindTexture(GL2 gl, String texKey){
		assetsGL.bindTexure(gl, texKey);		
	}
	
	public static void disableTexture(GL2 gl, String texKey){
		assetsGL.disableTexure(gl, texKey);
	}

	public static Texture getTexutre(String name) {
		return assetsGL.getTexture(name);
	}

	public static int getTextureId(String texKey) {
		return assetsGL.getTextureId(texKey);
	}

	public static void bindMultiTex(GL2 gl, String [] texNames) {
		assetsGL.bindMultiTexture(gl, texNames);
	}

	public static void disableMultiTexture(GL2 gl) {
		assetsGL.disableMultiTexture(gl);
	}

	public static void loadShaders(GL2 gl) throws IOException {
		AssetsGL.loadSahders(gl);
	}

	public static Shader getShader(String shaderTitle) {
		return AssetsGL.getShader(shaderTitle);
	}
}
