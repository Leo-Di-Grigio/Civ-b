package misc;

public class Const {
	
	public static final String title = "Civ";
	public static final int version = 0;
	public static final int subVersion = 48;
	
	// test data
	public static final int mapSizeX = 150;
	public static final int mapSizeY = 100;
	
	// assets
	public static final String assetsNative = "recources\\assets\\native\\";
	public static final String assetsGL = "recources\\assets\\opengl\\";
	
	// memory garbage collector
	public static final int tasksForGC = 10000; // every 10000 tasks - call gc();
	
	// constant image keys
	public static final String imgMinimap = "minimap_height";
	public static final String imgMinimapHeight = "minimap_height";
}
