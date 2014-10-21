package gui.misc;

import java.awt.Color;

import misc.Enums;

public class TableLine{
	
	public int columns = 0;
	
	public String [] line;
	public boolean [] hide;
	public Enums.TableMetadata metadata;
	
	public Color selectingColor = Color.black;
	
	public TableLine(int columns){
		this.columns = columns;
		line = new String[columns];
		hide = new boolean[columns];
		
		metadata = Enums.TableMetadata.NULL;
		for(int i = 0; i < columns; ++i){
			line[i] = new String("");
			hide[i] = false;
		}
	}
	
	public void setCell(int i, String text){
		line[i] = text;
	}
	
	public String getCell(int i){
		return line[i];
	}
	
	public void setHidden(int i){
		hide[i] = true;
	}
	
	public void setVisible(int i){
		hide[i] = false;
	}
	
	@Override
	public String toString() {
		String str = new String("");
		
		for(int i = 0; i < columns; ++i){
			if(!hide[i]){
				str += line[i] + ";";
			}
		}
		
		return str;
	}
}