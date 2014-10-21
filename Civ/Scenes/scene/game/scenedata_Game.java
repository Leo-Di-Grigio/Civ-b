package scene.game;

import java.io.IOException;

import net.Message;
import misc.Enums;
import scenedata.SceneData;
import scenedata.game.GameData;
import scenedata.game.Node;
import script.ScriptsNetwork;
import script.gui.gui_CurosorHide;
import script.gui.gui_CursorShow;
import script.gui.gui_ElementClick;
import script.gui.gui_ElementCollision;
import script.gui.gui_ElementSelect;
import script.gui.gui_UpdatePosition;
import script.painter.painter_SwitchScene;
import tasks.Task;

public class scenedata_Game extends SceneData {
	
	protected GameData gamedata;
	
	public scenedata_Game(GameData gamedata) {
		super(new scenegui_Game());
		this.gamedata = gamedata;
	}

	@Override
	public void execute(Task task) throws IOException {
		switch(task.type){
			case NETWORK_MESSAGE_READ:
				ScriptsNetwork.reciveMsg((Message)task.data);
				break;
				
			case MOUSE_MOVE:
				gui_ElementCollision.execute(gui);
				break;
		
			case MOUSE_RELEASED:
				gui_ElementClick.execute(gui);
				break;
			
			case KEYBOARD_PRESSED:
				game_KeyPressed.execute(gamedata, (String)task.data);
				break;
				
			case KEYBOARD_RELEASED:
				game_KeyReleased.execute(gamedata, gui, (String)task.data);
				break;
		
			case GUI_UPDATE_POSITION:
				gui_UpdatePosition.execute(gui);
				break;
			
			case GUI_CURSOR_SHOW:
				gui_CursorShow.execute(gui);
				break;
			
			case GUI_CURSOR_HIDE:
				gui_CurosorHide.execute(gui);
				break;
				
			case GUI_SELECTION_RESET:
				gui_ElementSelect.execute(gui, null);
				break;
			
			case GUI_SELECTION_SELECT:
				gui_ElementSelect.execute(gui, (String)task.data);
				break;
		
			case PAINTER_CHANGE_SCENE:
				painter_SwitchScene.execute((Enums.Scene)task.data);
				break;
			
			case SCENE_LOADING:
				gui_UpdatePosition.execute(gui);
				break;
			
			case SCENE_SELECTON:
				game_SelectNode.selectNode(gamedata);
				break;
				
			// scene events
			case GAME_SELECT_NODE:
				game_SelectNode.updateGuiData(gui, (Node)task.data);
				break;
				
			default: break;
		}
	}
}
