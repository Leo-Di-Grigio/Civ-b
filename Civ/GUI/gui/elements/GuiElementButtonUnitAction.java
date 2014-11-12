package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.media.opengl.GL3;

import recources.Recources;

public class GuiElementButtonUnitAction extends GuiElementButton {
	
	private Image actionIcon;
	
	public GuiElementButtonUnitAction(String title) {
		super(title);
	}
	
	public void setActionIcon(String textureTitle){
		if(textureTitle != null){
			this.actionIcon = Recources.getImage(textureTitle);
		}
		else{
			this.actionIcon = null;
		}
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
			
			if(this.actionIcon != null){
				g.drawImage(this.actionIcon, drawX, drawY, sizeX, sizeY, null);
			}
			
			g.drawString(this.text, drawX + 5, drawY + 15);
			
			if(selected){
				if(tooltip != null){
					tooltip.draw(g, tic);
				}
			}
		}
	}

	@Override
	public void draw(GL3 gl) {
		
	}
}
