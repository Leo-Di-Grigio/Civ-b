package gui.elements;

import java.awt.Graphics;

import javax.media.opengl.GL3;

import misc.Const;
import player.units.UnitInventory;
import recources.Recources;
import gui.GuiElement;

public class GuiElementInventory extends GuiElement {

	private UnitInventory inventory;
	
	public GuiElementInventory(String titile) {
		super(titile);
	}
	
	public void setInventory(UnitInventory inventory){
		this.inventory = inventory;
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		if(visible){
			g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
			
			if(inventory != null && inventory.items != null){
				for(int i = 0; i < inventory.items.length; ++i){
					g.drawImage(Recources.getImage(Const.imgInventorySlot), drawX + (i * 64 + 5), drawY + 5, 64, 64, null);
					
					if(inventory.items[i] != null){
						g.drawImage(Recources.getImage(Const.imgItemRecource), drawX + (i * 64 + 5), drawY + 5, 64, 64, null);
					}
				}
			}
		}
	}

	@Override
	public void draw(GL3 gl) {

	}
}
