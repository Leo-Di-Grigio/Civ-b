package gui.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.media.opengl.GL3;

import gui.GuiElement;
import gui.misc.MultiTableLine;

public class GuiElementMultiTable extends GuiElement {
	
	// data
	protected MultiTableLine list;
	protected int selectedLine = -1;
	
	public GuiElementMultiTable() {
		String [] arr = {""};
		list = new MultiTableLine(arr);
	}

	public void add(MultiTableLine line){
		list.addLine(line);
	}
	
	@Override
	public void draw(Graphics g) {
		if(visible){
			g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
			list.setDraw(drawX, drawY);
			
			g.setColor(Color.black);
			list.draw(g);
		}
	}

	@Override
	public void draw(GL3 gl) {

	}
}
