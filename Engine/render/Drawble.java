package render;

import java.awt.Graphics;

import javax.media.opengl.GL3;

public interface Drawble {
	public void draw(Graphics g);
	public void draw(GL3 gl);
}
