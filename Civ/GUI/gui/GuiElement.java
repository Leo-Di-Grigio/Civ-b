package gui;

import gui.tooltip.GuiTooltip;

import java.awt.Image;
import java.io.IOException;

import misc.Enums;
import misc.Environment;
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
		setTexture("null");
		setTextureSelected("null_selected");
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
		textureNormal = Recources.getImage(name);
	}
	
	public void setTexture(Image image){
		textureNormal = image;
	}
	
	public void setTextureSelected(String name){
		textureSelected = Recources.getImage(name);
	}
	
	public void setTextureSelected(Image image){
		textureSelected  = image;
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
	
	public GuiTooltip getToolTip(){
		return tooltip;
	}
}
