package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.media.opengl.GL2;

import recources.Recources;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;

import main.Config;
import misc.Enums;
import misc.Environment;
import gui.GuiElement;

public class GuiElementMinimap extends GuiElement {

	protected Image textureMinimap;
	protected Texture glTextureMinimap;
	
	// scaling
	protected float X;
	protected float Y;
	
	// map size
	protected int mapX;
	protected int mapY;
	
	public GuiElementMinimap(String title) {
		super(title);
	}
	
	public void setMinimapTexture(String textureName){
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			// Native mode
			this.textureMinimap = Recources.getImage(textureName);
			this.mapX = this.textureMinimap.getWidth(null);
			this.mapY = this.textureMinimap.getHeight(null);
			setSize(this.sizeX, this.sizeY);
		}
		else{
			// GL mode
			this.glTextureMinimap = Recources.getTexutre(textureName);
			this.mapX = this.glTextureMinimap.getWidth();
			this.mapY = this.glTextureMinimap.getHeight();
			setSize(this.sizeX, this.sizeY);
		}
	}
	
	public float getScaleFactorX(){
		return X;
	}
	
	public float getScaleFactorY(){
		return Y;
	}
	
	public int getMapSizeX(){
		return mapX;
	}
	
	public int getMapSizeY(){
		return mapY;
	}
	
	@Override
	public void setSize(int sizeX, int sizeY){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		X = ((float)sizeX - 4.0f)/(float)mapX;
		Y = ((float)sizeY - 4.0f)/(float)mapY;
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		int w = Environment.frameSizeX/32;
		int h = Environment.frameSizeY/32;
		int cameraX = Environment.cameraX;
		int cameraY = Environment.cameraY;
			
		// draw background and minimap texture
		g.drawImage(this.textureNormal, drawX, drawY, sizeX, sizeY, null);
		g.drawImage(this.textureMinimap, drawX + 2, drawY + 2, sizeX - 4, sizeY - 4, null);
			
		// draw camera rectangle
		g.setColor(Color.white); 
		if(cameraX + w < mapX){
			g.drawRect(drawX + 2 + (int)(cameraX * X), drawY + 2 +(int)(cameraY * Y),(int)(w * X), (int)(h * Y));
		}
		else{
			int delta = mapX - cameraX;
			g.drawRect(drawX + 2 + (int)(cameraX * X), drawY + 2 +(int)(cameraY * Y),(int)(delta * X), (int)(h * Y));
			g.drawRect(drawX + 2, drawY + 2 +(int)(cameraY * Y),(int)((w - delta) * X), (int)(h * Y));
		}
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		
	}
}
