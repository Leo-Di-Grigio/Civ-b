package gui.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.media.opengl.GL2;

import misc.Environment;

import com.jogamp.opengl.util.awt.TextRenderer;

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
	public void draw(GL2 gl, TextRenderer textrender) {
		if(this.visible){
			gl.glBegin(GL2.GL_QUADS);
			if(this.selected){
				gl.glColor4f(0.5f, 0.5f, 0.5f, 0.7f);
				gl.glVertex3f(drawX, drawY, 0);
				gl.glVertex3f(drawX, sizeY + drawY, 0);
				gl.glVertex3f(sizeX + drawX, sizeY + drawY, 0);
				gl.glVertex3f(sizeX + drawX, drawY, 0);
			}
			else{
				gl.glColor4f(0.4f, 0.4f, 0.4f, 0.7f);
				gl.glVertex3f(drawX, drawY, 0);
				gl.glVertex3f(drawX, sizeY + drawY, 0);
				gl.glVertex3f(sizeX + drawX, sizeY + drawY, 0);
				gl.glVertex3f(sizeX + drawX, drawY, 0);
			}
			gl.glEnd();
			
			textrender.beginRendering(Environment.frameSizeX, Environment.frameSizeY);
			textrender.draw(this.text, drawX + 10, Environment.frameSizeY - drawY - sizeY/2);
			textrender.endRendering();
		}
	}
}
