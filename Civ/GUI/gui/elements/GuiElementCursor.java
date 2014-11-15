package gui.elements;

import java.awt.Graphics;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.awt.TextRenderer;

import recources.Recources;
import misc.Const;
import misc.Environment;
import gui.GuiElement;

public class GuiElementCursor extends GuiElement {

	public GuiElementCursor(String title) {
		super(title);
		this.setSize(32, 32);
		this.setPosition(Environment.nodeDrawCursorX*32, Environment.nodeDrawCursorY*32);
		this.setVisible(true);
		this.setTexture("cursor_nodeselect");
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
		
		if(visible){
			Recources.setCursor(Const.cursorNull);
		}
		else{
			Recources.setCursor(Const.cursorVisible);
		}
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		g.drawImage(textureNormal, Environment.nodeDrawCursorX*32, Environment.nodeDrawCursorY*32, sizeX, sizeY, null);		
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		//
	}
}
