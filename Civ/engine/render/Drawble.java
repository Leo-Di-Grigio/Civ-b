package render;

import java.awt.Graphics;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.awt.TextRenderer;

public interface Drawble {
	public void draw(Graphics g, long tic);
	public void draw(GL2 gl, TextRenderer textrender);
}
