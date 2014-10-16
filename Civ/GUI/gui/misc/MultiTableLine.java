package gui.misc;

import java.awt.Graphics;
import java.util.Vector;

import javax.media.opengl.GL3;

import render.Drawble;

public class MultiTableLine implements Drawble {

	protected Vector<MultiTableLine> list;
	protected String [] data;
	
	protected boolean selected;
	
	protected int drawX = 0;
	protected int drawY = 0;
	protected int lineSize = 20;
	
	public MultiTableLine(String [] arr) {
		list = new Vector<MultiTableLine>();
		this.data = arr.clone();
	}
	
	public void setData(String [] arr){
		this.data = arr.clone();
	}
	
	public void addLine(String [] arr){
		list.add(new MultiTableLine(arr));
	}
	
	public void addLine(MultiTableLine line){
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
	
	private String printData(){
		String str = "";
		
		for(String item: data){
			str += item + " : ";
		}
		
		return str;
	}
	
	public void setDraw(int drawX, int drawY){
		this.drawX = drawX;
		this.drawY = drawY;
	}

	public int getLineSize() {
		return this.lineSize;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawString(printData(), drawX, drawY);
		
		for(int i = 0, shift = 0; i < list.size(); ++i){
			MultiTableLine item = list.get(i);
			
			shift = item.getSize() - 1;
			
			item.setDraw(drawX + 10, drawY + i * lineSize + shift * lineSize);
			item.draw(g);
		}
	}

	@Override
	public void draw(GL3 gl) {
		
	}
}
