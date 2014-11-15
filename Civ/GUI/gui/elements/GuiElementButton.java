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
		g.setColor(Color.white); // for text drawing
		
		if(this.selected){
			g.drawImage(this.textureSelected, drawX, drawY, sizeX, sizeY, null);
		}
		else{
			g.drawImage(this.textureNormal, drawX, drawY, sizeX, sizeY, null);
		}
		
		g.drawString(this.text, drawX + sizeX/2 - g.getFontMetrics().stringWidth(text)/2, drawY +  g.getFontMetrics().getHeight());
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		if(this.selected){
			glTexSelected.bind(gl);
			glTexSelected.enable(gl);
		}
		else{
			glTexNormal.bind(gl);
			glTexNormal.enable(gl);
		}
			
		gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(drawX, drawY, 0);
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(drawX, sizeY + drawY, 0);
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(sizeX + drawX, sizeY + drawY, 0);
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(sizeX + drawX, drawY, 0);
		gl.glEnd();
			
		if(this.selected){
			glTexSelected.disable(gl);
		}
		else{
			glTexNormal.disable(gl);
		}
			
		textrender.beginRendering(Environment.frameSizeX, Environment.frameSizeY);
		textrender.draw(this.text, drawX + 10, Environment.frameSizeY - drawY - sizeY/2);
		textrender.endRendering();
	}
}
