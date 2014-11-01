package gui.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.media.opengl.GL3;

import gui.GuiElement;

public class GuiElementTitle extends GuiElement {

	private String text = "";
	private Color color = Color.white;
	
	public GuiElementTitle(String title) {
		super(title);
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		g.setColor(color);
		g.drawString(text, drawX, drawY);
	}

	@Override
	public void draw(GL3 gl) {

	}
}
