package recources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;

import database.DB;
import recources.nongl.Tile;
import misc.Const;
import misc.Enums;
import misc.Log;

public class Recources {
	
	private static Assets assets;
	
	public Recources(Enums.RenderMode mode) throws FontFormatException, IOException{
		Log.msg("Recources loaded " + mode);
		
		switch(mode){
			case NATIVE:
				assets = new AssetsNative();
				break;
			
			case OPENGL:
				assets = new AssetsGL();
				break;
			
			default:
				assets = null;
				Log.err("Assets is not avaible");
				System.exit(0);
				break;
		}
		
		assets.init();
	}
	
	public static Image getImage(String name){
		return assets.getImage(name);
	}
	
	public static void addImage(String name, Tile tile){
		assets.addImage(name, tile);
	}

	public static void setCursor(String name) {
		assets.setCursor(name);
	}

	public static Font getFont() {
		return assets.getFont();
	}

	public static Image getUnitImage(int unitCode) {
		Image img = getImage(Const.imgNull);
		
		switch(unitCode){
			
			case DB.unitAvatar:
				return getImage(Const.imgUnitAvatar);
				
			case DB.buildingQuarter:
				return getImage(Const.imgUnitCity);
				
			case DB.unitNovice:
				return getImage(Const.imgUnitRecruit);
		}
		
		return img;
	}

	public static void loadTemperatureColor(int tMin, int tMax) {
		assets.loadTemperatureColor(tMin, tMax);
	}
}
