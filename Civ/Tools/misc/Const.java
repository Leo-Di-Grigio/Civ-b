package misc;

public class Const {
	
	// version
	public static final String title = "Civ";
	
	// assets
	public static final String assetsNative = "recources/assets/native/";
	public static final String assetsGL = "recources/assets/opengl/";
	
	// GL shaders
	public static final String glSahders = "shaders/";
	
	// memory garbage collector
	public static final int tasksForGC = 10000; // every 10000 tasks - call gc();
	
	// constant cursor keys
	public static final String cursorNull = "null";
	public static final String cursorVisible = "cursor";
	
	// constant image keys
	public static final String imgNull = "null"; 
	public static final String imgVoid = "void";
	public static final String imgNullSelected = "null_selected";
	public static final String imgIcon = "icon";
	public static final String imgMenu = "menu_background";	
	public static final String imgPane = "pane";
	public static final String imgWindow = "window";
	public static final String imgChat = "chat";
	public static final String imgChatSelected = "chat_selected";
	public static final String imgInventorySlot = "inventory_slot";
	public static final String imgSelect = "cursor_nodeselect";
	public static final String imgToolTip = "tooltip_background";
	
	// tech
	public static final String imgTechUnlearn = "tech_unlearn";
	public static final String imgTechLearn = "tech_learn";
	public static final String imgTechAvaible = "tech_avaible";

	// terrain atlas keys
	public static final String imgTerrainWater = "terrain_water";
	public static final String imgTerrainWaterBorder = "terrain_water_border";
	public static final String imgTerrainLand = "terrain_land";
	
	// action buttons keys
	public static final String imgActionMoveto = "action_moveto";
	public static final String imgActionCityBuild = "action_citybuild";
	public static final String imgActionBuildRecruit = "action_buildrecruit";
	public static final String imgActionMine = "action_mine";
	public static final String imgActionInventory = "action_inventory";
	public static final String imgActionInteract = "action_interact";
	
	// action interact keys
	public static final String imgInteractAttack = "interact_attak";
	public static final String imgInteractTalk = "interact_talk";
	public static final String imgInteractWorkAt = "interact_workat";
	public static final String imgInteractRepair = "interact_repair";
	public static final String imgInteractBuildUpdate = "interact_buildUpdate";
	public static final String imgInteractBuildCityBuilding = "interact_buildBuilding";
	
	// buttons
	public static final String imgButton = "button";
	public static final String imgButtonSelected = "button_select";
	public static final String imgButtonEndTurn = "button";
	public static final String imgButtonMenu = "button_menu";
	public static final String imgButtonMenuSelect = "button_menu_select";
	
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
	public static final String imgMinimapGeology = "minimap_geology";
	public static final String imgMinimapTemperature = "minimap_temperature";
	
	// items
	public static final String imgItemRecource = "item_recource";
	public static final String imgItemCampPack = "item_camppack";
	
	// color
	public static final float [] colorBlack = {0.0f, 0.0f, 0.0f};
	public static final float [] colorWhite = {1.0f, 1.0f, 1.0f};
	public static final float [] colorRed   = {1.0f, 0.0f, 0.0f};
	public static final float [] colorGreen = {0.0f, 1.0f, 0.0f};
	public static final float [] colorBlue  = {0.0f, 0.0f, 1.0f};
}
