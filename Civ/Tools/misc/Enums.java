package misc;

public class Enums {
	
	public static enum RenderMode {
		NATIVE,
		OPENGL;
	}
	
	public static enum Scene {
		MENU,
		MENU_EXIT,
		MENU_SETTINGS,
		MENU_CONNECT,
		MENU_NEWGAME,
		MENU_LOADGAME,
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
}
