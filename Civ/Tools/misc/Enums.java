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
		GAME_JOIN_SUCCESS, //(String)gameMapSeed -> (Long)
		GAME_JOIN_FAILED, //(String)error message
		
		// Scene Game
		GAME_SELECT_NODE,
		
		// Painter
		PAINTER_CHANGE_SCENE, //(String)sceneKey
		
		// Network
		NETWORK_MESSAGE_READ,
		NETWORK_GAMELIST; //(Message)message
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
}
