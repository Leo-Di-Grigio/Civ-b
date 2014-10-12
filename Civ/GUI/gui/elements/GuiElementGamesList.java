package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.media.opengl.GL3;

import gui.GuiElement;
import misc.TableLine;

public class GuiElementGamesList extends GuiElement {
	
	protected int selectedLine = -1;
	protected int lineSize = 20;
	protected Vector<TableLine> gamesList;
	
	public GuiElementGamesList() {
		super();
		setSize(400, 400);
		setTexture("pane");
		
		gamesList = new Vector<TableLine>();
	}
	
	public int getLineSize(){
		return lineSize;
	}
	
	public TableLine getSelectedLine(){
		if(selectedLine == -1){
			return null;
		}
		else{
			return gamesList.get(selectedLine);
		}
	}
	
	public void updateList(String data){
		gamesList.clear();
		String [] list = data.split(":");
		
		int size = Integer.valueOf(list[0]);
		
		for(int i = 0; i < size; ++i){
			TableLine line = new TableLine(3);
			
			line.setCell(0, list[i*3 + 1]);
			line.setCell(1, list[i*3 + 2]);
			line.setCell(2, list[i*3 + 3]);
			
			gamesList.add(line);
		}
	}
	
	public void select(int lineId){
		if(lineId >= gamesList.size() || lineId < 0){
			this.selectedLine = -1; // no selected lines
		}
		else{
			this.selectedLine = lineId;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		if(visible){
			g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
			
			for(int i = 0; i < gamesList.size(); ++i){
				if(i == selectedLine){
					g.setColor(Color.black);
					g.fillRect(drawX + 10, drawY + i * lineSize + 5, sizeX - 20, lineSize);
					g.setColor(Color.white);
					g.drawString(gamesList.get(i).toString(), drawX + 10, drawY + i * lineSize + 20);
				}
				else{
					g.setColor(Color.black);
					g.drawString(gamesList.get(i).toString(), drawX + 10, drawY + i * lineSize + 20);
				}
			}
		}
	}

	@Override
	public void draw(GL3 gl) {

	}
}
