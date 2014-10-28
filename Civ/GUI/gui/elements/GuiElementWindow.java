package gui.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.media.opengl.GL3;

import gui.GuiElement;

public class GuiElementWindow extends GuiElement {
	
	public String userText = "";
	public String enterText = "";
	public GuiElementWindow(String title){
		super(title);
	}

	public void setEnterText(String string) {
		this.enterText = string;
	}
	
	@Override
	public void draw(Graphics g) {
		if(this.visible){
			g.drawImage(this.textureNormal, drawX, drawY, sizeX, sizeY, null);
			g.setColor(Color.black);
			g.drawString(text, drawX + sizeX/2 - g.getFontMetrics().stringWidth(text)/2, drawY +  g.getFontMetrics().getHeight());
			g.drawString(enterText + " " + userText, drawX + 5, drawY + sizeY/2 + g.getFontMetrics().getHeight() - 12);
		}
	}

	@Override
	public void draw(GL3 gl) {

	}
}
