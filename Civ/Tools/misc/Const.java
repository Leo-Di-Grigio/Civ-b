package misc;

public class Const {
	
	// version
	public static final String title = "Civ";
	
	// assets
	public static final String assetsNative = "recources/assets/native/";
	public static final String assetsGL = "recources/assets/opengl/";
	
	// memory garbage collector
	public static final int tasksForGC = 10000; // every 10000 tasks - call gc();
	
	// constant cursor keys
	public static final String cursorNull = "null";
	public static final String cursorVisible = "cursor";
	// constant image keys
	public static final String imgNull = "null"; 
	public static final String imgVoid = "void";
	public static final String imgIcon = "icon";
	public static final String imgMenu = "menu_background";	
	public static final String imgPane = "pane";
	public static final String imgWindow = "window";
	public static final String imgChat = "chat";
	public static final String imgChatSelected = "chat_selected";
	
	// terrain atlas keys
	public static final String imgTerrainWater = "terrain_water";
	public static final String imgTerrainWaterBorder = "terrain_water_border";
	public static final String imgTerrainLand = "terrain_land";
	
	// action buttons keys
	public static final String imgActionMoveto = "action_moveto";
	public static final String imgActionCityBuild = "action_citybuild";
	public static final String imgActionBuildRecruit = "action_buildrecruit";
	public static final String imgActionMine = "action_mine";
	
	public static final String imgButton = "button";
	public static final String imgButtonSelected = "button_select";
	public static final String imgButtonEndTurn = "button";
	
	// units
	public static final String imgUnitPlayerAtlas = "atlas_players";
	public static final String imgUnitAvatar = "unit_avatar";
	public static final String imgUnitRecruit = "unit_recrut";
	public static final String imgUnitCity   = "unit_city";
	
	// misc
	public static final String imgWaypoint = "null";
	
	// minimap
	public static final String imgMinimap = "minimap";
	public static final String imgMinimapHeight = "minimap_height";
	public static final String imgMinimapGeology = "minima_geology";
}
