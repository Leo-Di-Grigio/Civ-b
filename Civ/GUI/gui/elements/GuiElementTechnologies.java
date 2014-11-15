package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;

import main.Config;
import misc.Const;
import misc.Enums;
import recources.Recources;
import database.tech.TeamTech;
import database.tech.Tech;
import gui.GuiElement;

public class GuiElementTechnologies extends GuiElement {

	private TeamTech tech;
	
	private static Image texUnlearn;
	private static Image texLearn;
	private static Image texAvaible;
	
	private static Texture glTexUnlearn;
	private static Texture glTexLearn;
	private static Texture glTexAvaible;
	
	// draw
	int nowRow;
	int nowLine;
	int nextRow;
	int nextLine;
	boolean direct;
	
	public GuiElementTechnologies(String titile) {
		super(titile);
		
		if(Config.renderMode == Enums.RenderMode.NATIVE){
			// Native
			texUnlearn = Recources.getImage(Const.imgTechUnlearn);
			texLearn = Recources.getImage(Const.imgTechLearn);
			texAvaible = Recources.getImage(Const.imgTechAvaible);
		}
		else{
			// GL
			glTexUnlearn = Recources.getTexutre(Const.imgTechUnlearn);
			glTexLearn = Recources.getTexutre(Const.imgTechLearn);
			glTexAvaible = Recources.getTexutre(Const.imgTechAvaible);
		}
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
	
	private Texture glTechStatus(Tech tech){
		if(tech.learned()){
			return glTexLearn;
		}
		else{
			for(Tech parent: tech.getParents()){
				if(!parent.learned()){
					return glTexUnlearn;
				}
			}
			
			return glTexAvaible;
		}
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
		g.setColor(Color.black);
		HashMap<Integer, Tech> list = tech.getList();

		reset();
		for(Tech tech: list.values()){
			calcNextTechPosition();
			g.drawImage(techStatus(tech), drawX + nowRow * 170 + 10, drawY + nowLine*65 + 10, 150, 48, null);
			g.drawString(tech.getTitle(), drawX + nowRow * 170 + 60, drawY + nowLine*65 + 30);
		}
	}

	@Override
	public void draw(GL2 gl, TextRenderer textrender) {
		bindTexture(gl, glTexNormal);
		drawQuad(gl, drawX, drawY, sizeX, sizeY);
		disableTexture(gl, glTexNormal);
		
		HashMap<Integer, Tech> list = tech.getList();
		
		reset();
		for(Tech tech: list.values()){
			calcNextTechPosition();
			bindTexture(gl, glTechStatus(tech));
			drawQuad(gl, drawX + nowRow * 170 + 10, drawY + nowLine*65 + 10, 150, 48);
			disableTexture(gl, glTechStatus(tech));
			drawText(textrender, tech.getTitle(), drawX + nowRow * 170 + 60, drawY + nowLine*65 + 30);
		}
	}
	
	private void reset(){
		nowRow = 0;
		nowLine = 0;
		nextRow = 0;
		nextLine = 0;
		direct = false;
	}
	
	private void calcNextTechPosition(){
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
	}
}
