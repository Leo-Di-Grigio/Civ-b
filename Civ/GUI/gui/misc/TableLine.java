package gui.misc;

import java.awt.Color;
import misc.Enums;

public class TableLine{
	
	public int columns = 0;
	
	public String [] line;
	public boolean [] hide;
	public Enums.TableMetadata metadata;
	
	// Color
	private Color selectingColor;
	private float [] glSelectingColor;
	
	public TableLine(int columns){
		this.columns = columns;
		line = new String[columns];
		hide = new boolean[columns];
		
		metadata = Enums.TableMetadata.NULL;
		
		selectingColor = Color.black;
		glSelectingColor = new float[3];
		
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
	
	public void setColor(Color color){
		selectingColor = color;
		
		glSelectingColor[0] = ((float)color.getRed())/256;
		glSelectingColor[1] = ((float)color.getGreen())/256;
		glSelectingColor[2] = ((float)color.getBlue())/256;
	}
	
	public void setColor(float [] color){
		glSelectingColor = color;
	}
	
	public Color getColor(){
		return selectingColor;
	}
	
	public float[] getGlColor(){
		return glSelectingColor;
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