package gui.tooltip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.media.opengl.GL3;

import misc.Const;
import misc.Environment;
import recources.Recources;
import render.Drawble;

public class GuiTooltip implements Drawble {
	
	// data
	private String text;
	
	// size
	private int sizeX;
	private int sizeY;
	
	// texture
	private Image texture;
	
	public GuiTooltip(String text) {
		this.text = text;
		this.texture = Recources.getImage(Const.imgToolTip);
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setSize(int sizeX, int sizeY){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public void setTexture(String textureName){
		this.texture = Recources.getImage(textureName);
	}
	
	public void setTexture(Image texture){
		this.texture = texture;
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		int x = Environment.mouseX + 30;
		int y = Environment.mouseY;
		
		g.drawImage(texture, x, y, sizeX, sizeY, null);
		g.setColor(Color.white);
		g.drawString(text, x, y + g.getFontMetrics().getHeight()/2 + 5);
	}

	@Override
	public void draw(GL3 gl) {
		
	}
}
