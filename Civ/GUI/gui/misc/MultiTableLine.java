package gui.misc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.media.opengl.GL3;

import render.Drawble;

public class MultiTableLine implements Drawble {

	protected Vector<MultiTableLine> list;
	protected String [] data;
	
	protected boolean selected;
	
	protected int level = 0;
	protected int drawX = 0;
	protected int drawY = 0;
	protected int lineSize = 20;
	
	public MultiTableLine(String [] arr){
		list = new Vector<MultiTableLine>();
		this.data = arr.clone();
	}
	
	public void addLine(MultiTableLine line) {
		line.level = this.level + 1;
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
			str += item + ": ";
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
		g.setColor(Color.black);
		g.drawString(printData(), drawX, drawY);
		
		int shift = 0;
		for(int i = 0; i < list.size(); ++i){
			MultiTableLine item = list.get(i);
			
			item.setDraw(drawX + 10, drawY + (i + shift) * lineSize + 20);
			item.draw(g);
			System.out.println("Level: " + item.level + " size: " + item.getSize());
			
			shift += item.getSize() - 1;
		}
	}

	@Override
	public void draw(GL3 gl) {
		
	}
}
