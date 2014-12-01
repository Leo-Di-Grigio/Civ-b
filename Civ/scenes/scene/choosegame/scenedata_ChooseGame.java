package scene.choosegame;

import java.io.IOException;

import net.Message;
import misc.Enums;
import scene.menu_newgame.game_JoinGame;
import scene.menu_newgame.gui_UpdateGamesList;
import scenedata.SceneData;
import script.ScriptsNetwork;
import script.gui.gui_ElementClick;
import script.gui.gui_ElementCollision;
import script.gui.gui_ElementSelect;
import script.gui.gui_UpdatePosition;
import script.painter.painter_SwitchScene;
import tasks.Task;

public class scenedata_ChooseGame extends SceneData {

	public scenedata_ChooseGame() {
		super(new scenegui_ChooseGame());
	}

	@Override
	public void execute(Task task) throws IOException {
		switch(task.type){
			case NETWORK_MESSAGE_READ:
				ScriptsNetwork.reciveMsg((Message)task.data);
				break;
			
			case DATA_GAMELIST:
				gui_UpdateGamesList.execute(gui, (String)task.data);
				break;
				
			case MOUSE_MOVE:
				gui_ElementCollision.execute(gui);
				break;
		
			case MOUSE_RELEASED:
				gui_ElementClick.execute(gui);
				break;
		
			case GUI_UPDATE_POSITION:
				gui_UpdatePosition.execute(gui);
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
				ScriptsNetwork.createConnection();
				gui_UpdatePosition.execute(gui);
				break;
			
			case GAME_JOIN_SUCCESS:
				game_JoinGame.sucess((String)task.data);
				break;
			
			case GAME_JOIN_FAILED:
				game_JoinGame.failed((String)task.data);
				break;
				
			default: break;
		}
	}
}
