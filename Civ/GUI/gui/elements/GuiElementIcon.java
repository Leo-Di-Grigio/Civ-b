package gui.elements;

import java.awt.Graphics;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.awt.TextRenderer;

import gui.GuiElement;

public class GuiElementIcon extends GuiElement {

	public GuiElementIcon(String title) {
		super(title);
	}

	@Override
	public void draw(Graphics g, long tic) {
		if(visible){
			if(selected){
				g.drawImage(textureSelected, drawX, drawY, sizeX, sizeY, null);
			}
			else{
				g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
			}
		}
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		
	}
}
