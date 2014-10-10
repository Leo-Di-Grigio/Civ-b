package recources;

import java.awt.Image;

import recources.nongl.Tile;
import misc.Log;

public class AssetsGL extends Assets {

	public AssetsGL() {
		super();
		Log.msg("Assets GL loaded");
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
}
