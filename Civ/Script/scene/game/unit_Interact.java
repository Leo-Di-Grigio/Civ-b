package scene.game;

import java.io.IOException;

import net.Message;
import net.Message.Prefix;
import network.Network;
import database.ConstAction;
import misc.Enums;
import misc.Environment;
import misc.Log;
import painter.Painter;
import player.units.Unit;
import scenedata.game.GameData;
import scenedata.game.GameMap;
import script.gui.ScriptGui;
import tasks.Task;

public class unit_Interact extends ScriptGui {
	
	private GameData gamedata;
	private GameMap map;
	private int nodeX;
	private int nodeY;
	
	private Unit unit;
	
	public unit_Interact(GameData gamedata, Unit unit) {
		this.gamedata = gamedata;
		this.map = gamedata.map;
		this.unit = unit;
	}
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute unit_Interact");

		if(unit != null){
			Painter.addTask(new Task(Enums.Task.SCENE_SUBSCRIBER_ADD, this));
		}
	}
	
	@Override
	public Task preexecute(Task task, Object data) throws IOException {
		if(task.type == Enums.Task.MOUSE_MOVE){
			if(nodeX != Environment.nodeSelectedX || nodeY != Environment.nodeSelectedY){
				nodeX = Environment.nodeSelectedX;
				nodeY = Environment.nodeSelectedY;
			}
			
			return task;
		}
		
		if(task.type == Enums.Task.GAME_SELECT_NODE){
			Log.debug("Execute unit_Interact x: " + Environment.nodeSelectedX + " y: " + Environment.nodeSelectedY);
			
			int interactScenario = ConstAction.intreactBreak;
			// interact scenario
			
			//
			
			if(interactScenario != ConstAction.intreactBreak){
				String msgData = "" + ConstAction.interact + ":" + interactScenario + ":" + this.unit.id + ":" + nodeX + ":" + nodeY;
				Message msg = new Message(Prefix.PLAYER_ACTION, msgData);
				Network.sendMsg(msg);
			}
			Painter.addTask(new Task(Enums.Task.SCENE_SUBSCRIBER_DEL, null));
			task.blocked = true;
		}
		
		return task;
	}
}
