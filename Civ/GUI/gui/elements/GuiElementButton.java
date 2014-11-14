package gui.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.media.opengl.GL2;

import gui.GuiElement;

public class GuiElementButton extends GuiElement {
		
	public GuiElementButton(String title) {
		super(title);
	}

	@Override
	public void draw(Graphics g, long tic) {
		if(this.visible){
			g.setColor(Color.white); // for text drawing
			
			if(this.selected){
				g.drawImage(this.textureSelected, drawX, drawY, sizeX, sizeY, null);
			}
			else{
				g.drawImage(this.textureNormal, drawX, drawY, sizeX, sizeY, null);
			}
			
			g.drawString(this.text, drawX + sizeX/2 - g.getFontMetrics().stringWidth(text)/2, drawY +  g.getFontMetrics().getHeight());
		}
	}

	@Override
	public void draw(GL2 gl) {
		
	}
}
