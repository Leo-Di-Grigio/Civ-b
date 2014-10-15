package scene.prepare;

import java.io.IOException;

import net.Message;
import misc.Enums;
import scene.game.game_Data;
import scene.game.game_Msg;
import scenedata.SceneData;
import scenedata.game.GameData;
import script.ScriptsNetwork;
import script.gui.gui_ElementClick;
import script.gui.gui_ElementCollision;
import script.gui.gui_ElementSelect;
import script.gui.gui_UpdatePosition;
import script.painter.painter_SwitchScene;
import tasks.Task;

public class scenedata_Prepare extends SceneData {
	
	protected GameData gamedata;
	
	public scenedata_Prepare(GameData gamedata) {
		super(new scenegui_Prepare());
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
				gui_UpdatePosition.execute(gui);
				break;
			
			// Data
			case GAME_OBJ_PLAYER:
				game_Data.objPlayer(gamedata, (String)task.data);
				break;
				
			case GAME_OBJ_TEAM:
				game_Data.objTeam(gamedata, (String)task.data);
				break;
				
			case GAME_UPD_PLAYER:
				game_Data.updPlayer(gamedata, (String)task.data);
				break;
			
			case GAME_UPD_TEAM:
				game_Data.updTeam(gamedata, (String)task.data);
				break;
			
			case GAME_MSG:
				game_Msg.msg((String)task.data);
				break;
				
			default: break;
		}
	}
}
