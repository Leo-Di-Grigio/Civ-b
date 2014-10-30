package script.unit;

import gui.elements.GuiElementTable;
import gui.misc.TableLine;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import database.ConstAction;
import database.DB;
import algorithms.PathFinding;
import net.Message;
import net.Message.Prefix;
import network.Network;
import painter.Painter;
import player.units.Unit;
import player.units.UnitsMng;
import main.Config;
import misc.Enums;
import misc.Environment;
import misc.Log;
import scene.game.scenegui_Game;
import scenedata.game.GameData;
import scenedata.game.GameMap;
import script.gui.ScriptGui;
import tasks.Task;

public class unit_MoveTo extends ScriptGui {
	
	private GameData gamedata;
	private GameMap map;
	private int nodeX;
	private int nodeY;
	
	protected ArrayList<Point> path;
	
	protected Unit unit;
	
	public unit_MoveTo(GameData gamedata) {
		this.gamedata = gamedata;
		this.map = gamedata.map;
	}
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute unit_MoveTo");
		
		GuiElementTable table = (GuiElementTable)task.sceneGui.get(scenegui_Game.uiUnitSelect);
		
		if(table != null){
			TableLine line = table.getSelectedLine();
			int unitId = Integer.parseInt(line.getCell(0));
			this.unit = gamedata.units.getUnit(unitId);
			Painter.addTask(new Task(Enums.Task.SCENE_SUBSCRIBER_ADD, this));
		}
	}
	
	@Override
	public Task preexecute(Task task, Object data) throws IOException {
		if(task.type == Enums.Task.MOUSE_MOVE){
			if(nodeX != Environment.nodeSelectedX || nodeY != Environment.nodeSelectedY){
				nodeX = Environment.nodeSelectedX;
				nodeY = Environment.nodeSelectedY;
				
				if(Config.gameShowUnitPathPrevew){
					path = PathFinding.getPath(unit.x, unit.y, nodeX, nodeY, DB.getMovementType(unit.type), map.height, map.sizeX, map.sizeY);
					unit.setPath(gamedata.units, path);
				}
			}
			
			return task;
		}
		
		if(task.type == Enums.Task.GAME_SELECT_NODE){
			Log.debug("Execute unit_MoveTo x: " + Environment.nodeSelectedX + " y: " + Environment.nodeSelectedY);
			
			path = PathFinding.getPath(unit.x, unit.y, nodeX, nodeY, DB.getMovementType(unit.type), map.height, map.sizeX, map.sizeY);
			unit.setPath(gamedata.units, path);
			
			// send player action
			if(gamedata.units.haveWay(unit.id)){
				Network.sendMsg(new Message(Prefix.PLAYER_ACTION, "" + ConstAction.moveTo + ":" + this.unit.id + ":" + Environment.nodeSelectedX + ":" + Environment.nodeSelectedY));
			}
		
			Painter.addTask(new Task(Enums.Task.SCENE_SUBSCRIBER_DEL, null));
			task.blocked = true; // block future execution of GAME_SELECT_NODE task
		}
		
		return task;
	}
	
	public static void addWay(GameData gamedata, String [] arr) {
		int unitId = Integer.parseInt(arr[1]);
		int toX = Integer.parseInt(arr[2]);
		int toY = Integer.parseInt(arr[3]);
		
		addWay(gamedata.units, unitId, toX, toY);
	}
	
	public static void addWay(UnitsMng units, int unitId, int toX, int toY){
		Unit unit = units.getUnit(unitId);
		ArrayList<Point> way = PathFinding.getPath(unit.x, unit.y, toX, toY, DB.getMovementType(unit.type), units.map.height, units.map.sizeX, units.map.sizeY);
		
		if(way != null){
			units.addWay(unitId, way);
		}
		else{
			units.removeWay(unitId);
		}
	}
}
