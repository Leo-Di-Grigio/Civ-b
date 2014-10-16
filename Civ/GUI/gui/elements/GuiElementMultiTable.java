package gui.elements;

import java.awt.Graphics;
import java.util.Vector;

import javax.media.opengl.GL3;

import gui.GuiElement;
import gui.misc.MultiTableLine;

public class GuiElementMultiTable extends GuiElement {

	// header
	protected String [] title;
	
	// data
	protected Vector<MultiTableLine> list;
	
	public GuiElementMultiTable() {
		list = new Vector<MultiTableLine>();
	}

	@Override
	public void draw(Graphics g) {
		
	}

	@Override
	public void draw(GL3 gl) {

	}
}
