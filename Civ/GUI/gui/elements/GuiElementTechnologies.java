package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.media.opengl.GL3;

import database.tech.TeamTech;
import database.tech.Tech;
import gui.GuiElement;

public class GuiElementTechnologies extends GuiElement {

	private TeamTech tech;
	
	public GuiElementTechnologies(String titile) {
		super(titile);
	}
	
	public void update(TeamTech tech){
		this.tech = tech;
	}

	@Override
	public void draw(Graphics g, long tic) {
		if(visible){
			g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
			
			// test
			g.setColor(Color.black);
			HashMap<Integer, Tech> list = tech.getList();
			int counter = 0;
			for(Tech tech: list.values()){
				g.drawString(tech.getTitle(), drawX + 15, drawY + 15 + counter*g.getFontMetrics().getHeight());
				counter++;
			}
		}
	}

	@Override
	public void draw(GL3 gl) {

	}
}
