package script.unit;

import gui.GUI;
import gui.elements.GuiElementTable;
import gui.misc.TableLine;

import java.io.IOException;

import net.Message;
import net.Message.Prefix;
import network.Network;
import painter.Painter;
import misc.Enums;
import misc.Environment;
import misc.Log;
import scene.game.scenegui_Game;
import script.gui.ScriptGui;
import tasks.Task;

public class unit_MoveTo extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute unit_MoveTo");
		Painter.addTask(new Task(Enums.Task.SCENE_SUBSCRIBER_ADD, this));
	}
	
	@Override
	public void preexecute(Task task) throws IOException {
		if(task.type == Enums.Task.GAME_SELECT_NODE){
			Log.debug("Execute unit_MoveTo x: " + Environment.nodeSelectedX + " y: " + Environment.nodeSelectedY);
			
			{
				GUI gui = (GUI)task.sceneGui;
				GuiElementTable table = (GuiElementTable)gui.get(scenegui_Game.uiUnitSelect);
				
				if(table != null){
					TableLine line = table.getSelectedLine();
					int unitId = Integer.parseInt(line.getCell(0));
					
					// send player action
					Network.sendMsg(new Message(Prefix.PLAYER_ACTION, "" + unitId + ":" + Environment.nodeSelectedX + ":" + Environment.nodeSelectedY));
				}
			}
			
			Painter.addTask(new Task(Enums.Task.SCENE_SUBSCRIBER_DEL, null));
			task.blocked = true; // block future execution of GAME_SELECT_NODE task
		}
	}
}
