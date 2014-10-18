package misc;

public class Enums {
	
	public static enum RenderMode {
		NATIVE,
		OPENGL;
	}
	
	public static enum Scene {
		MENU,
		EXIT,
		SETTINGS,
		LOAD,
		CONNECT,
		PREPEARE,
		CHOOSE_GAME,
		GAME;
	}
	
	public static enum Task {
		// mouse click tasks
		MOUSE_MOVE,
		MOUSE_RELEASED,
		MOUSE_CLICKED,
		MOUSE_PRESSED,
		
		// keyboard
		KEYBOARD_PRESSED,
		KEYBOARD_RELEASED,
		KEYBOARD_TYPED,
		
		// Gui
		GUI_UPDATE_POSITION,
		GUI_CURSOR_SHOW,
		GUI_CURSOR_HIDE,
		GUI_SELECTION_RESET,  //(GUI)gui, null
		GUI_SELECTION_SELECT, //(GUI)gui, (String)guiElementTitle
		
		// Scene
		SCENE_LOADING,
		SCENE_SELECTON,
		
		// Game generating
		GAME_JOIN_SUCCESS, //(String)gameMapSeed:(String)sizeX:(String)sizeY
		GAME_JOIN_FAILED,  //(String)error message
		
		// Scene ChooseGame
		GAME_SELECT_NODE,
		DATA_GAMELIST,     //(String)size:arrData
		
		// Data
		GAME_MSG,
		GAME_OBJ_PLAYER, //
		GAME_OBJ_TEAM,   //
		GAME_OBJ_UNIT,
		
		// Data update
		GAME_UPD_PLAYER,
		GAME_UPD_TEAM,
		GAME_UPD_UNIT,
		
		// Data delete
		GAME_DEL_PLAYER,
		GAME_DEL_TEAM,
		GAME_DEL_UNIT,
		
		// Painter
		PAINTER_CHANGE_SCENE, //(String)sceneKey
		
		// Network
		NETWORK_MESSAGE_READ;
	}
	
	public static enum GuiPosition {
		TOP_LEFT,
		TOP_CENTER,
		TOP_RIGHT,
		CENTER_LEFT,
		CENTER,
		CENTER_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_CENTER,
		BOTTOM_RIGHT,
	}
	
	public static enum Direct {
		UP,
		DOWN,
		LEFT,
		RIGHT,
		UP_LEFT,
		UP_RIGHT,
		DOWN_LEFT,
		DOWN_RIGHT;
	}
	
	public static enum Terrain {
		WATER,
		LAND;
	}
	
	public static enum MapDrawMode {
		HEIGHT,
		TERRAIN;
	}
	
	public static enum TableMetadata {
		NULL,
		TEAM,
		PLAYER;
	}
}
