package scene.game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;

import player.units.Unit;
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
				
			case MOUSE_WHEEL:
				game_WheelScroll.scroll(gui, gamedata, (MouseWheelEvent)task.data);
				break;
			
			case KEYBOARD_PRESSED:
				game_KeyPressed.execute(gamedata, gui, (KeyEvent)task.data);
				break;
				
			case KEYBOARD_RELEASED:
				game_KeyReleased.execute(gamedata, gui, (KeyEvent)task.data);
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
				
			case GUI_UNIT_SELECT:
				game_PlayerActions.updateTableSelection(gui, gamedata, (Unit)task.data);
				break;
		
			case PAINTER_CHANGE_SCENE:
				painter_SwitchScene.execute((Enums.Scene)task.data);
				break;
			
			case SCENE_LOADING:
				gui_UpdatePosition.execute(gui);
				gui_Tech.loadTech(gui, gamedata);
				game_Turn.nextTurn(gui, gamedata);
				break;
			
			case SCENE_SELECTON:
				game_SelectNode.selectNode(gui, gamedata);
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
				
			case GAME_OBJ_INVENTORY:
				game_Data.objInventory(gui, gamedata, (String)task.data);
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
				
			case GAME_UPD_INVENTORY:
				game_Data.updInventory(gui, gamedata, (String)task.data);
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
				gamedata.turn = true;
				game_Turn.nextTurn(gui, gamedata);
				break;
			
			case GAME_TURN_END:
				gamedata.turn = false;
				break;
				
			case GAME_MSG:
				game_Msg.msg((String)task.data);
				break;
			
			case CHAT_MSG:
				game_Chat.msg(gui, (String)task.data);
				break;
				
			case PLAYER_ACTION:
				game_Data.playerAction(gui, gamedata, (String)task.data);
				break;
				
			default: break;
		}
	}
}
