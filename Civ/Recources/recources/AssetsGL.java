package recources;

import java.awt.Font;
import java.awt.Image;

import recources.nongl.Tile;
import misc.Log;

public class AssetsGL extends Assets {

	public AssetsGL() {
		super();
		Log.debug("Assets GL loaded");
	}

	@Override
	public void init() {

	}

	@Override
	public Image getImage(String name) {
		return null;
	}

	@Override
	public void addImage(String name, Tile tile) {
	
	}

	@Override
	public void setCursor(String name) {
		
	}

	@Override
	public Font getFont() {
		return null;
	}

	@Override
	public void loadTemperatureColor(int tMin, int tMax) {
		
	}

	@Override
	public Image getItem(int itemIcon) {
		return null;
	}
}
