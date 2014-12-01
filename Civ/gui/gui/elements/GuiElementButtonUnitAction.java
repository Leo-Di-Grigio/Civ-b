package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.media.opengl.GL2;

import main.Config;
import misc.Enums;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;

import recources.Recources;

public class GuiElementButtonUnitAction extends GuiElementButton {
	
	private Image actionIcon;
	private Texture glActionIcon;
	
	public GuiElementButtonUnitAction(String title) {
		super(title);
	}
	
	public void setActionIcon(String textureTitle){
		if(textureTitle != null){
			if(Config.renderMode == Enums.RenderMode.NATIVE){
				this.actionIcon = Recources.getImage(textureTitle);
			}
			else{
				this.glActionIcon = Recources.getTexutre(textureTitle);
			}
		}
		else{
			this.actionIcon = null;
		}
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
			
		if(this.actionIcon != null){
			g.drawImage(this.actionIcon, drawX, drawY, sizeX, sizeY, null);
		}
			
		g.drawString(this.text, drawX + 5, drawY + 15);
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		bindTexture(gl);
		drawQuad(gl, drawX, drawY, sizeX, sizeY);
		disableTexture(gl);
		
		bindTexture(gl, glActionIcon);
		drawQuad(gl, drawX, drawY, sizeX, sizeY);
		disableTexture(gl, glActionIcon);
		
		drawText(textrender, this.text, drawX + 10, drawY + sizeY/2);
	}
}
