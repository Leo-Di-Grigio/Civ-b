package gui.elements;

import java.awt.Graphics;
import javax.media.opengl.GL3;
import gui.GuiElement;

public class GuiElementTechnologies extends GuiElement {

	public GuiElementTechnologies(String titile) {
		super(titile);
	}

	@Override
	public void draw(Graphics g, long tic) {
		if(visible){
			g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
		}
	}

	@Override
	public void draw(GL3 gl) {

	}
}
