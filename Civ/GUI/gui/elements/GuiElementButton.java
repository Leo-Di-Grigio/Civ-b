package gui.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.media.opengl.GL3;

import gui.GuiElement;

public class GuiElementButton extends GuiElement {
		
	public GuiElementButton(String title) {
		super(title);
	}

	@Override
	public void draw(Graphics g) {
		if(this.visible){
			g.setColor(Color.white); // for text drawing
			
			if(this.selected){
				g.drawImage(this.textureSelected, drawX, drawY, sizeX, sizeY, null);
				g.drawString(this.text, drawX + 5, drawY + 15);
			}
			else{
				g.drawImage(this.textureNormal, drawX, drawY, sizeX, sizeY, null);
				g.drawString(this.text, drawX + 5, drawY + 15);
			}
		}
	}

	@Override
	public void draw(GL3 gl) {
		
	}
}
