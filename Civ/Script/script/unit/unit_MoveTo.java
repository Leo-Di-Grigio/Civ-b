package script.unit;

import gui.elements.GuiElementTable;
import gui.misc.TableLine;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import data.units.ConstUnits;
import algorithms.PathFinding;
import net.Message;
import net.Message.Prefix;
import network.Network;
import painter.Painter;
import player.units.Unit;
import player.units.UnitsMng;
import misc.Enums;
import misc.Environment;
import misc.Log;
import scene.game.scenegui_Game;
import scenedata.game.GameData;
import scenedata.game.GameMap;
import script.gui.ScriptGui;
import tasks.Task;

public class unit_MoveTo extends ScriptGui {
	
	protected GameMap map;
	protected int nodeX;
	protected int nodeY;
	
	protected PathFinding finder;
	protected ArrayList<Point> path;
	
	protected Unit unit;
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute unit_MoveTo");
		
		GuiElementTable table = (GuiElementTable)task.sceneGui.get(scenegui_Game.uiUnitSelect);
		
		if(table != null){
			TableLine line = table.getSelectedLine();
			int unitId = Integer.parseInt(line.getCell(0));
			this.unit = UnitsMng.getUnit(unitId);
			this.finder = new PathFinding();
			Painter.addTask(new Task(Enums.Task.SCENE_SUBSCRIBER_ADD, this));
		}
	}
	
	@Override
	public Task preexecute(Task task, Object data) throws IOException {
		if(task.type == Enums.Task.MOUSE_MOVE){
			if(nodeX != Environment.nodeSelectedX || nodeY != Environment.nodeSelectedY){
				nodeX = Environment.nodeSelectedX;
				nodeY = Environment.nodeSelectedY;
				
				GameData gamedata = (GameData)data;
				this.map = gamedata.map;
				
				path = finder.getPath(unit.x, unit.y, nodeX, nodeY, ConstUnits.getMovementType(unit.type), map.height, map.sizeX, map.sizeY);
				unit.setPath(path);
				
				if(path == null){
					Log.err("no way");
				}
			}
			
			return task;
		}
		
		if(task.type == Enums.Task.GAME_SELECT_NODE){
			Log.debug("Execute unit_MoveTo x: " + Environment.nodeSelectedX + " y: " + Environment.nodeSelectedY);
			
			// send player action
			Network.sendMsg(new Message(Prefix.PLAYER_ACTION, "" + this.unit.id + ":" + Environment.nodeSelectedX + ":" + Environment.nodeSelectedY));
		
			Painter.addTask(new Task(Enums.Task.SCENE_SUBSCRIBER_DEL, null));
			task.blocked = true; // block future execution of GAME_SELECT_NODE task
		}
		
		return task;
	}
}
