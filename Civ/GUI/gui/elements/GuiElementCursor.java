package gui.elements;

import java.awt.Graphics;

import javax.media.opengl.GL3;

import recources.Recources;
import misc.Environment;
import gui.GuiElement;

public class GuiElementCursor extends GuiElement {

	public GuiElementCursor() {
		super();
		this.setSize(32, 32);
		this.setPosition(Environment.nodeDrawCursorX*32, Environment.nodeDrawCursorY*32);
		this.setVisible(true);
		this.setTexture("cursor_nodeselect");
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
		
		if(visible){
			Recources.setCursor("null");
		}
		else{
			Recources.setCursor("cursor");
		}
	}
	
	@Override
	public void draw(Graphics g) {
		if(visible){
			g.drawImage(textureNormal, Environment.nodeDrawCursorX*32, Environment.nodeDrawCursorY*32, sizeX, sizeY, null);
		}
	}

	@Override
	public void draw(GL3 gl) {

	}
}
