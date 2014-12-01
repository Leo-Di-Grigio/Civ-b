package gui.elements;

import java.awt.Graphics;
import java.awt.Image;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.awt.TextRenderer;

import recources.Recources;
import gui.GuiElement;
import gui.misc.MultiTableLine;

public class GuiElementMultiTable extends GuiElement {
	
	public static final int lineSize = 20;
	
	// data
	protected MultiTableLine list;
	protected int selected = -1;
	
	// slider
	protected int elementsMax = 0;
	protected Image textureSlider;
	protected float scaling = 0;
	protected int scroll = 0;
	protected int sliderPos = 0;
	protected int sliderScale = 0;
	protected boolean scrolled = true;
	 
	public GuiElementMultiTable(String title) {
		super(title);
		list = new MultiTableLine("");
		list.setRoot();
		
		MultiTableLine line = new MultiTableLine("");
		list.addLine(line);
		
		this.textureSlider = Recources.getImage("slider");
	}

	public void addLine(MultiTableLine line){
		list.addLine(line);
	}

	public void select(int line) {
		list.select(selected, false);
		list.select(line + scroll + 1, true);
		this.selected = line + scroll + 1;
	}
	
	public int getTableSize(){
		return list.getSize() - 2; // -2 because .root() and .first(); 
	}
	
	public void scroll(float percent, int mouseY){
		this.scroll = (int)((float)getTableSize() * percent);
		this.scrolled = true;
	}
	
	@Override
	public void setSize(int sizeX, int sizeY) {
		this.elementsMax = (sizeY - 10) / GuiElementMultiTable.lineSize;
		super.setSize(sizeX, sizeY);
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		// afterscrolling update slider position
		if(scrolled){
			scrolled = false;
			scaling = (float)elementsMax / (float)getTableSize();
			sliderScale = (int)((sizeY - 10) * scaling);
			sliderPos = (int)(scroll * scaling * GuiElementMultiTable.lineSize);
		
			if(sliderPos + sliderScale >= sizeY){
				System.out.println("size " + sizeY + " pos " + (sliderPos + sliderScale));
				sliderPos = sizeY - sliderScale - 5;
			}
		}
			
		// draw Table
		g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
		g.drawImage(textureSlider, drawX + sizeX - 15, drawY + sliderPos + 5, 15, sliderScale, null);
		list.setDraw(drawX, drawY, scroll, sizeX, drawX);
		list.draw(g, tic);
	}


	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		
	}
}
