package misc;

public class Const {
	
	// version
	public static final String title = "Civ";
	public static final int version = 0;
	public static final int subVersion = 68;

	// assets
	public static final String assetsNative = "recources\\assets\\native\\";
	public static final String assetsGL = "recources\\assets\\opengl\\";
	
	// memory garbage collector
	public static final int tasksForGC = 10000; // every 10000 tasks - call gc();
	
	// constant image keys
	public static final String imgMenu = "menu_background";
	public static final String imgMinimap = "minimap";
	public static final String imgMinimapHeight = "minimap_height";
	
	// terrain atlas keys
	public static final String imgTerrainWater = "terrain_water";
	public static final String imgTerrainWaterBorder = "terrain_water_border";
	public static final String imgTerrainLand = "terrain_land";
	
	// units
	public static final String imgUnitAvatar = "unit_avatar";
}
