package gui.misc;

import java.awt.Color;

import misc.Enums;

public class TableLine{
	
	public int columns = 0;
	
	public String [] line;
	public Enums.TableMetadata metadata;
	
	public Color selectingColor = Color.black;
	
	public TableLine(int columns){
		line = new String[columns];
		metadata = Enums.TableMetadata.NULL;
		for(int i = 0; i < columns; ++i){
			line[i] = new String("");
		}
	}
	
	public void setCell(int i, String text){
		line[i] = text;
	}
	
	public String getCell(int i){
		return line[i];
	}
	
	@Override
	public String toString() {
		String str = new String("");
		
		for(String item: line){
			str += item + ";";
		}
		
		return str;
	}
}