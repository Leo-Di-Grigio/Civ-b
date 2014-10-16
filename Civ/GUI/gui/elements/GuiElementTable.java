package gui.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.media.opengl.GL3;

import gui.GuiElement;
import gui.misc.TableLine;

public class GuiElementTable extends GuiElement {
	
	protected int selectedLine = -1;
	protected int lineSize;
	
	protected int collumns;
	protected Vector<TableLine> list;
	
	public GuiElementTable(int collumns) {
		super();
		setTexture("pane");
		
		this.lineSize = 20;
		this.collumns = collumns;
		list = new Vector<TableLine>();
	}
	
	public int getLineSize(){
		return lineSize;
	}

	public void setCollumns(int collumns) {
		this.collumns = collumns;
	}
	
	public int getCollumns(){
		return collumns;
	}
	
	public TableLine getSelectedLine(){
		if(selectedLine == -1){
			return null;
		}
		else{
			return list.get(selectedLine);
		}
	}
	
	public void updateList(String data){
		list.clear();
		String [] arr = data.split(":");
		
		int size = Integer.valueOf(arr[0]);
		
		for(int i = 0; i < size; ++i){
			TableLine line = new TableLine(collumns);
			
			for(int j = 1; j <= collumns; ++j){
				line.setCell(j - 1, arr[i*collumns + j]);
			}
			
			list.add(line);
		}
	}
	
	public void select(int lineId){
		if(lineId >= list.size() || lineId < 0){
			this.selectedLine = -1; // no selected lines
		}
		else{
			this.selectedLine = lineId;
		}
	}
	
	public void add(TableLine line){
		list.add(line);
	}
	
	@Override
	public void draw(Graphics g) {
		if(visible){
			g.drawImage(textureNormal, drawX, drawY, sizeX, sizeY, null);
			
			for(int i = 0; i < list.size(); ++i){
				if(i == selectedLine){
					g.setColor(Color.black);
					g.fillRect(drawX + 10, drawY + i * lineSize + 5, sizeX - 20, lineSize);
					g.setColor(Color.white);
					g.drawString(list.get(i).toString(), drawX + 10, drawY + i * lineSize + 20);
				}
				else{
					g.setColor(Color.black);
					g.drawString(list.get(i).toString(), drawX + 10, drawY + i * lineSize + 20);
				}
			}
		}
	}

	@Override
	public void draw(GL3 gl) {

	}

	public void remove(int collumn, String data) { // search specify (String)data in (int)collumn and remove line while data finded
		for(int i = 0; i < list.size(); ++i){
			if(list.get(i).getCell(collumn).compareTo(data) == 0){
				list.remove(i);
				return;
			}
		}
	}
}
