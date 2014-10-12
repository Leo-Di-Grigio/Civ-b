package scene.menu_newgame;

import java.io.IOException;

import network.Message;
import misc.Enums;
import scene.painter.painter_SwitchScene;
import scenedata.SceneData;
import script.gui.gui_ElementClick;
import script.gui.gui_ElementCollision;
import script.gui.gui_ElementSelect;
import script.gui.gui_UpdatePosition;
import script.network.net_CreateConnection;
import script.network.net_ReciveMsg;
import tasks.Task;

public class data_SceneMenuNewGame extends SceneData {

	public data_SceneMenuNewGame() {
		super(new gui_SceneMenuNewGame());
	}

	@Override
	public void execute(Task task) throws IOException {
		switch(task.type){
			case NETWORK_MESSAGE_READ:
				net_ReciveMsg.execute((Message)task.data);
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
				net_CreateConnection.execute();
				gui_UpdatePosition.execute(gui);
				break;
				
			default: break;
		}
	}

}
