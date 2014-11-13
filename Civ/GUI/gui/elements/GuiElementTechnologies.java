package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

import javax.media.opengl.GL3;

import misc.Const;
import recources.Recources;
import database.tech.TeamTech;
import database.tech.Tech;
import gui.GuiElement;

public class GuiElementTechnologies extends GuiElement {

	private TeamTech tech;
	
	private static Image texUnlearn;
	private static Image texLearn;
	private static Image texAvaible;
	
	public GuiElementTechnologies(String titile) {
		super(titile);
		texUnlearn = Recources.getImage(Const.imgTechUnlearn);
		texLearn = Recources.getImage(Const.imgTechLearn);
		texAvaible = Recources.getImage(Const.imgTechAvaible);
	}
	
	public void update(TeamTech tech){
		this.tech = tech;
	}

	private Image techStatus(Tech tech){
		if(tech.learned()){
			return texLearn;
		}
		else{
			for(Tech parent: tech.getParents()){
				if(!parent.learned()){
					return texUnlearn;
				}
			}
			
			return texAvaible;
		}
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		if(visible){
			g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
			
			// test
			g.setColor(Color.black);
			HashMap<Integer, Tech> list = tech.getList();

			// stone
			
			int nowRow = 0;
			int nowLine = 0;
			int nextRow = 0;
			int nextLine = 0;
			
			boolean direct = false;
			
			for(Tech tech: list.values()){
				nowRow = nextRow;
				nowLine = nextLine;
				
				if(!direct){
					if(nextRow + 1 == 4){
						direct = !direct;
						nextLine++;
						nextRow--;
					}
					
					nextRow++;
				}
				else{
					if(nextRow - 1 == -1){
						direct = !direct;
						nextLine++;
						nextRow++;
					}
					
					nextRow--;
				}
				
				g.drawImage(techStatus(tech), drawX + nowRow * 170 + 10, drawY + nowLine*65 + 10, 150, 48, null);
				g.drawString(tech.getTitle(), drawX + nowRow * 170 + 60, drawY + nowLine*65 + 30);
			}
		}
	}

	@Override
	public void draw(GL3 gl) {

	}
}
