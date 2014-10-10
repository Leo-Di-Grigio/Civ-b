package recources;

import java.awt.Image;

import recources.nongl.Tile;

abstract public class Assets {
	
	abstract public void init();
	
	// get methods
	abstract public Image getImage(String name);
	abstract public void setCursor(String name);
	abstract public void addImage(String name, Tile tile);
}
