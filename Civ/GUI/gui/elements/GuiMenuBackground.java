package gui.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.media.opengl.GL2;

import misc.Const;
import misc.Environment;
import misc.ToolsConst;

import com.jogamp.opengl.util.awt.TextRenderer;

import gui.GuiElement;

public class GuiMenuBackground extends GuiElement {

	public GuiMenuBackground(String title) {
		super(title);
		setTexture(Const.imgMenu);
	}

	@Override
	public void draw(Graphics g, long tic) {
		setSize(Environment.frameSizeX, Environment.frameSizeY);
		g.drawImage(textureNormal, 0, 0, sizeX, sizeY, null);
		
		g.setColor(Color.white);
		g.drawString("" + Const.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion, 0, 10);
		g.drawString("2014 (c) Leo di Grigio and Sivalent", 0, 20);
		g.drawString("Scene: Prepare", 0, 30);
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		setSize(Environment.frameSizeX, Environment.frameSizeY);
		
		glTexNormal.bind(gl);
		glTexNormal.enable(gl);
		
		gl.glBegin(GL2.GL_QUADS);
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(drawX, drawY, 0);
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(drawX, sizeY + drawY, 0);
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(sizeX + drawX, sizeY + drawY, 0);
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(sizeX + drawX, drawY, 0);
		gl.glEnd();
		
		glTexNormal.disable(gl);
		
		textrender.beginRendering(Environment.frameSizeX, Environment.frameSizeY);
		textrender.draw("" + Const.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion, 0, Environment.frameSizeY - 10);
		textrender.draw("2014 (c) Leo di Grigio and Sivalent", 0, Environment.frameSizeY - 20);
		textrender.draw("Scene: Settings", 0, Environment.frameSizeY - 30);
		textrender.endRendering();
	}
}
