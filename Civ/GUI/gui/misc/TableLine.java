package gui.misc;

public class TableLine{
	
	public int columns = 0;
	public String [] line;
	
	public TableLine(int columns){
		line = new String[columns];
		
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