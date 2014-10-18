package gui.misc;

import gui.elements.GuiElementMultiTable;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.media.opengl.GL3;

import render.Drawble;

public class MultiTableLine implements Drawble {

	protected Vector<MultiTableLine> list;
	protected String data;
	
	protected boolean root = false;
	protected boolean selected = false;
	
	protected int drawX = 0;
	protected int drawY = 0;
	
	protected int drawSelectingX = 0;
	protected int sizeX = 0;
	protected int scroll = 0;
	
	protected Color selectColor;
	
	public MultiTableLine(String data){
		list = new Vector<MultiTableLine>();
		this.data = data;
		this.selectColor = Color.white;
	}
	
	public MultiTableLine(String [] arr){
		list = new Vector<MultiTableLine>();
		this.data = buildString(arr);
		this.selectColor = Color.white;
	}
	
	public MultiTableLine(String [] arr, Color selectColor){
		list = new Vector<MultiTableLine>();
		this.data = buildString(arr);
		this.selectColor = selectColor;
	}
	
	public void addLine(MultiTableLine line) {
		list.add(line);
	}
	
	public int getSize(){
		if(list.size() == 0){
			return 1;
		}
		else{
			int size = 1;
			
			for(MultiTableLine line: list){
				size += line.getSize();
			}
			
			return size;
		}
	}
	
	private String buildString(String [] arr){
		String str = "";
		
		for(String item: arr){
			str += item + ": ";
		}
		
		return str;
	}
	
	public void setRoot(){
		this.root = true;
	}
	
	public void setDraw(int drawX, int drawY, int scroll, int sizeX, int drawSelectingX){
		this.drawX = drawX;
		this.drawY = drawY;
		this.drawSelectingX = drawSelectingX;
		this.sizeX = sizeX;
		this.scroll = scroll;
	}

	public int select(int line, boolean value) {
		line--;
		
		if(line == 0){
			// if == 0 -> this line, search complete
			this.selected = value;
			return 0;
		}
		else{
			if(list.size() > 0){
				// if list has elements - recursively check every element in list
				int i = 0;
				int select = line;
				
				do{
					select = list.get(i).select(select, value);
					i++;
				}
				while(i < list.size() && select != 0);
				
				if(select == 0){
					// yep, line in list is selected
					return 0;
				}
				else{
					// no, in list no exist selected line, continue searching
					return select--;
				}
			}
			else{
				// so, not this line, continue searching
				return line--;
			}
		}
	}
	
	public int deselect(int line){
		if(line == -1){
			return 0;
		}
		if(line == 0){
			this.selected = false;
			return 0;
		}
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		if(!root){
			if(selected){
				g.setColor(this.selectColor);
				g.fillRect(drawSelectingX + 10, drawY + 5, sizeX - 40, GuiElementMultiTable.lineSize);
			}
		
			g.setColor(Color.black);
			g.drawString(data, drawX, drawY - scroll * GuiElementMultiTable.lineSize);
		}
		
		int shift = 0;
		for(int i = 0; i < list.size(); ++i){
			MultiTableLine item = list.get(i);
			
			item.setDraw(drawX + 10, drawY + (i + shift) * GuiElementMultiTable.lineSize + 20, scroll, sizeX, drawSelectingX);
			item.draw(g);
			
			
			shift += item.getSize() - 1;
		}
	}

	@Override
	public void draw(GL3 gl) {
		
	}
}
