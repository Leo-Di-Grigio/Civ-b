package recources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;

import recources.nongl.Tile;

abstract public class Assets {
	
	abstract public void init() throws FontFormatException, IOException;
	
	// get methods
	abstract public Image getImage(String name);
	abstract public void setCursor(String name);
	abstract public void addImage(String name, Tile tile);
	abstract public Font getFont();
	abstract public void loadTemperatureColor(int tMin, int tMax);
}
