package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.media.opengl.GL2;

import database.DB;
import misc.Const;
import player.units.Unit;
import recources.Recources;
import gui.GuiElement;

public class GuiElementUnits extends GuiElement {
	
	private int basicPositionX;
	private int basicPositionY;
	
	private ArrayList<Unit> units;
	private int selected = 0;
	private int unitsPerLine = 8;
	
	public int lines = 1;
	
	public GuiElementUnits(String titile) {
		super(titile);
	}

	public void clear(){
		this.units = null;
		this.selected = 0;
	}
	
	public void add(ArrayList<Unit> units){
		this.units = units;
		this.lines = units.size()/unitsPerLine;
		
		if(this.lines == 0){
			this.lines = 1;
		}
		else{
			if(units.size() % unitsPerLine > 0){
				this.lines++;
			}
		}
		
		this.setSize(300, lines * 36 + 5);
		super.setPosition(basicPositionX, basicPositionY - lines * 36 + 5);
	}
	
	public void select(int id){
		this.selected = id;
	}
	
	public void select(int line, int row){
		this.selected = line * unitsPerLine + row;
	}
	
	public Unit getSelectedUnit() {
		if(selected < units.size()){
			return units.get(selected);
		}
		else{
			return null;
		}
	}

	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		this.basicPositionX = x;
		this.basicPositionY = y;
	}
	
	@Override
	public void draw(Graphics g, long tic) {
		if(visible && units != null){
			g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
			
			int index = 0;
			
			for(int i = 0; i < lines; ++i){
				for(int j = 0; j < unitsPerLine; ++j){
					
					index = i * unitsPerLine + j;
					if(index < units.size()){
						Unit unit = units.get(i * unitsPerLine + j);
					
						if(unit != null){
							// draw unit icon
							g.drawImage(Recources.getUnitImage(unit.type), drawX + 36*j + 5, drawY + 36*i + 5, 32, 32, null);
							
							// draw HP bar
							if(unit.hp > 0){
								// get HP bar width
								int maxHP = DB.getUnitHP(unit.type);
								float hpPerPixel = 32.0f/maxHP;
								int barWidth = (int)(unit.hp * hpPerPixel);
								
								g.setColor(Color.red);
								g.fillRect(drawX + 36*j + 5, drawY + 36*i + 34, barWidth, 3);
							}
							
							// draw selection box
							if(index == selected){
								g.drawImage(Recources.getImage(Const.imgSelect), drawX + 36*j + 5, drawY + 36*i + 5, 32, 32, null);
							}
						}
					}
					else{
						break;
					}
				}
			}
		}
	}
	
	@Override
	public void draw(GL2 gl) {
		
	}
}