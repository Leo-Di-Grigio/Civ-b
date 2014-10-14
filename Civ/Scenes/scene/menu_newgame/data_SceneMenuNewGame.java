package scene.menu_newgame;

import java.io.IOException;

import net.Message;
import misc.Enums;
import scene.painter.painter_SwitchScene;
import scenedata.SceneData;
import script.gui.gui_ElementClick;
import script.gui.gui_ElementCollision;
import script.gui.gui_ElementSelect;
import script.gui.gui_UpdatePosition;
import script.network.netScripts;
import tasks.Task;

public class data_SceneMenuNewGame extends SceneData {

	public data_SceneMenuNewGame() {
		super(new gui_SceneMenuNewGame());
	}

	@Override
	public void execute(Task task) throws IOException {
		switch(task.type){
			case NETWORK_MESSAGE_READ:
				netScripts.reciveMsg((Message)task.data);
				break;
			
			case NETWORK_GAMELIST:
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
				netScripts.createConnection();
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
