package scene.game;

import gui.elements.GuiElementTable;

import java.io.IOException;

import net.Message;
import misc.Enums;
import scenedata.SceneData;
import scenedata.game.GameData;
import script.Script;
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
				
			case GUI_TABLE_UPDATESELECTION:
				game_PlayerActions.updateTableSelection(gui, gamedata, (GuiElementTable)task.data);
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
				
			case SCENE_SUBSCRIBER_ADD:
				subscriberAdd((Script)task.data, gamedata);
				break;
				
			case SCENE_SUBSCRIBER_DEL:
				subscriberRemove();
				break;
				
			// scene events
			case GAME_SELECT_NODE:
				game_SelectNode.updateGuiData(gui, gamedata, (String)task.data);
				break;
				
			// Data
			case GAME_OBJ_PLAYER:
				game_Data.objPlayer(gui, gamedata, (String)task.data);
				break;
					
			case GAME_OBJ_TEAM:
				game_Data.objTeam(gui, gamedata, (String)task.data);
				break;
					
			case GAME_OBJ_UNIT:
				game_Data.objUnit(gui, gamedata, (String)task.data);
				break;
					
			case GAME_UPD_PLAYER:
				game_Data.updPlayer(gui, gamedata, (String)task.data);
				break;
				
			case GAME_UPD_TEAM:
				game_Data.updTeam(gui, gamedata, (String)task.data);
				break;
					
			case GAME_UPD_UNIT:
				game_Data.updUnit(gui, gamedata, (String)task.data);
				break;
				
			case GAME_DEL_PLAYER:
				game_Data.delPlayer(gui, gamedata, (String)task.data);
				break;
				
			case GAME_DEL_TEAM:
				game_Data.delTeam(gui, gamedata, (String)task.data);
				break;
					
			case GAME_DEL_UNIT:
				game_Data.delUnit(gui, gamedata, (String)task.data);
				break;
			
			case GAME_TURN:
				game_Turn.nextTurn(gui, gamedata);
				break;
				
			case GAME_MSG:
				game_Msg.msg((String)task.data);
				break;
				
			case PLAYER_ACTION:
				game_Data.playerAction(gui, gamedata, (String)task.data);
				break;
				
			default: break;
		}
	}
}
