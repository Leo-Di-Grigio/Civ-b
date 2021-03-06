package gui;

import gui.tooltip.GuiTooltip;

import java.awt.Image;
import java.io.IOException;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;

import main.Config;
import misc.Const;
import misc.Enums;
import misc.Environment;
import misc.Log;
import recources.Recources;
import render.Drawble;
import script.gui.ScriptGui;
import tasks.Task;

abstract public class GuiElement implements Drawble {
	
	// size params
	protected int x;
	protected int y;
	protected int sizeX;
	protected int sizeY;
	
	// draw params
	protected Enums.GuiPosition position;
	protected int drawX;
	protected int drawY;
	
	// texture params
	protected Image textureNormal;
	protected Image textureSelected;
	
	// texture GL
	protected Texture glTexNormal;
	protected Texture glTexSelected;
	
	// flags
	protected boolean visible;
	protected boolean selected;
	
	// label
	protected String title = "";
	protected String text = "";
	
	// scripts
	protected GuiLayer layer;
	private ScriptGui script;
	
	// tooltip
	protected GuiTooltip tooltip;
	
	public GuiElement(String titile) {
		this.title = titile;
		position = Enums.GuiPosition.TOP_LEFT;
		layer = new GuiLayer(0);
		setTexture(Const.imgNull);
		setTextureSelected(Const.imgNullSelected);
	}

	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		updateDrawPosition(0, 0, Environment.frameSizeX, Environment.frameSizeY);
	}
	
	public void setPositionType(Enums.GuiPosition type){
		this.position = type;
		updateDrawPosition(0, 0, Environment.frameSizeX, Environment.frameSizeY);
	}
	
	private void setDrawPosition(int x, int y){
		this.drawX = x;
		this.drawY = y;
	}
	
	public void setLayer(int layer){
		this.layer.value = layer;
	}
	
	public void updateDrawPosition(int xPos, int yPos, int w, int h){
		switch(position){
			case BOTTOM_CENTER:
				setDrawPosition(xPos + w/2 - sizeX/2 + x, yPos + h - sizeY + y);
				break;
			
			case BOTTOM_LEFT:
				setDrawPosition(xPos + x, yPos + h - sizeY + y);
				break;
				
			case BOTTOM_RIGHT:
				setDrawPosition(xPos + w - sizeX + x, yPos + h - sizeY + y);
				break;
				
			case CENTER:
				setDrawPosition(xPos + w/2 - sizeX/2 + x, yPos + h/2 - sizeY/2 + y);
				break;
				
			case CENTER_LEFT:
				setDrawPosition(xPos + x, yPos + h/2 - sizeY/2 + y);
				break;
				
			case CENTER_RIGHT:
				setDrawPosition(xPos + w - sizeX + x, yPos + h/2 - sizeY/2 + y);
				break;
				
			case TOP_CENTER:
				setDrawPosition(xPos + w/2 - sizeX/2 + x, yPos + y);
				break;
				
			case ABSOLUTE:	
			case TOP_LEFT:
				setDrawPosition(xPos + x, yPos + y);
				break;
				
			case TOP_RIGHT:
				setDrawPosition(xPos + w - sizeX + x, yPos + y);
				break;
				
			default: break;
		}
	}

	public void setSize(int sizeX, int sizeY){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public void setTexture(String name){
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			textureNormal = Recources.getImage(name);
		}
		else{
			glTexNormal = Recources.getTexutre(name);
		}
	}
	
	public void setTexture(Image image){
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			textureNormal = image;
		}
		else{
			Log.err("Use NATIVE mode to setImage");
		}
	}
	
	public void setTextureSelected(String name){
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			textureSelected = Recources.getImage(name);
		}
		else{
			glTexSelected = Recources.getTexutre(name);
		}
	}
	
	public void setTextureSelected(Image image){
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			textureSelected  = image;
		}
		else{
			Log.err("Use NATIVE mode to setImage");
		}
	}
	
	public void setTexture(Texture tex){
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			Log.err("Use OPENGL mode to setTextutre");
		}
		else{
			glTexNormal = tex;
		}
	}
	
	public void setTextureSelected(Texture tex){
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			Log.err("Use OPENGL mode to setTextutre");
		}
		else{
			glTexSelected = tex;
		}
	}
	
	public boolean checkCollision() {
		if(this.visible &&
		   Environment.mouseX > drawX && Environment.mouseX < drawX + sizeX &&
		   Environment.mouseY > drawY && Environment.mouseY < drawY + sizeY)
		{
			return true;
		}
		else{
			return false;
		}
	}

	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	public boolean getVisible(){
		return this.visible;
	}
	
	public void setSelected(boolean select) {
		this.selected = select;
	}

	public void setText(String text){
		this.text = text;
	}
	
	// scripts
	public void setScript(ScriptGui script){
		this.script = script;
	}
	
	public int getDrawX(){
		return drawX;
	}
	
	public int getDrawY(){
		return drawY;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getSizeX(){
		return sizeX;
	}
	
	public int getSizeY(){
		return sizeY;
	}
	
	public ScriptGui getScript(){
		return script;
	}
	
	public String getTitle(){
		return title;
	}

	public void executeScript(Task task) throws IOException {
		this.script.execute(task);
	}
	
	public void setTooltip(GuiTooltip tooltip){
		this.tooltip = tooltip;
	}
	
	public GuiTooltip getTooltip(){
		return tooltip;
	}
	
	// GL
	protected void drawText(TextRenderer textrender, String text, int drawX, int drawY){
		textrender.beginRendering(Environment.frameSizeX, Environment.frameSizeY);
		textrender.draw(text, drawX, Environment.frameSizeY - drawY);
		textrender.endRendering();
	}
	
	protected void bindTexture(GL2 gl, Texture tex){
		tex.bind(gl);
		tex.enable(gl);
	}
	
	protected void disableTexture(GL2 gl, Texture tex) {
		tex.disable(gl);
	}
	
	protected void bindTexture(GL2 gl, String texKey){
		Recources.bindTexture(gl, texKey);
	}
	
	protected void disableTexture(GL2 gl, String texKey) {
		Recources.disableTexture(gl, texKey);
	}
	
	protected void bindTexture(GL2 gl){
		if(this.selected){
			glTexSelected.bind(gl);
			glTexSelected.enable(gl);
		}
		else{
			glTexNormal.bind(gl);
			glTexNormal.enable(gl);
		}
	}
	
	protected void disableTexture(GL2 gl){
		if(this.selected){
			glTexSelected.disable(gl);
		}
		else{
			glTexNormal.disable(gl);
		}
	}
	
	protected void drawQuad(GL2 gl, int drawX, int drawY, int sizeX, int sizeY){
		gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex2f(drawX, 			drawY	     );
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex2f(drawX, 			drawY + sizeY);
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex2f(drawX + sizeX,  	drawY + sizeY);
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex2f(drawX + sizeX, 	drawY        );
		gl.glEnd();
	}
}
