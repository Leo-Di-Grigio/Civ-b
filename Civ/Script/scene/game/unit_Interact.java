package scene.game;

import gui.GUI;
import gui.elements.GuiElementButtonUnitAction;

import java.io.IOException;
import java.util.HashSet;

import net.Message;
import net.Message.Prefix;
import network.Network;
import database.ConstAction;
import database.DB;
import misc.Const;
import misc.Enums;
import misc.Environment;
import misc.Log;
import painter.Painter;
import player.units.Unit;
import scenedata.game.GameData;
import scenedata.game.GameMap;
import scenedata.game.Node;
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
		
		resetInteractMenu(task.sceneGui);
		
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
			
			if(nodeY >= 0 && nodeY < map.sizeY){
				Node selectedNode = map.map[nodeX][nodeY];
				if(selectedNode != null){
					showInteractMenu(selectedNode, task.sceneGui);
				}
			}
			
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
	
	public static void resetInteractMenu(GUI gui){
		GuiElementButtonUnitAction button0 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton0);
		GuiElementButtonUnitAction button1 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton1);
		GuiElementButtonUnitAction button2 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton2);
		GuiElementButtonUnitAction button3 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton3);
		GuiElementButtonUnitAction button4 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton4);
		GuiElementButtonUnitAction button5 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton5);
		
		button0.setActionIcon(null);
		button0.setScript(null);
		button1.setActionIcon(null);
		button1.setScript(null);
		button2.setActionIcon(null);
		button2.setScript(null);
		button3.setActionIcon(null);
		button3.setScript(null);
		button4.setActionIcon(null);
		button4.setScript(null);
		button5.setActionIcon(null);
		button5.setScript(null);
		
		button0.setVisible(false);
		button1.setVisible(false);
		button2.setVisible(false);
		button3.setVisible(false);
		button4.setVisible(false);
		button5.setVisible(false);
	}
	
	private void showInteractMenu(Node node, GUI gui){
		GuiElementButtonUnitAction button0 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton0);
		GuiElementButtonUnitAction button1 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton1);
		GuiElementButtonUnitAction button2 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton2);
		GuiElementButtonUnitAction button3 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton3);
		GuiElementButtonUnitAction button4 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton4);
		GuiElementButtonUnitAction button5 = (GuiElementButtonUnitAction)gui.get(scenegui_Game.uiInteractButton5);
		
		HashSet<Integer> nodeUnits = node.getAll();
		
		boolean enemy = false;
		boolean city = false; 
		boolean nodeupd = false;
		
		Unit cityUnit = null;
		Unit nodeupdUnit = null;
		
		if(nodeUnits.size() > 0){
			for(Integer unitId: nodeUnits){
				Unit nodeUnit = gamedata.units.getUnit(unitId);
			
				if(DB.isQuarter(nodeUnit.type)){
					city = true;
					cityUnit = nodeUnit;
				}
				
				if(DB.isNodeUpdata(nodeUnit.type)){
					nodeupd = true;
					nodeupdUnit = nodeUnit;
				}
			
				if(nodeUnit.playerId != gamedata.clientId){
					enemy = true;
				}
			}
			
			if(city){
				button0.setActionIcon(Const.imgUnitCity);
				button0.setScript(new interact_City(gamedata, cityUnit, unit));
				
				button1.setActionIcon(Const.imgInteractBuildCityBuilding);
				button1.setScript(new interact_CityBuild(gamedata, cityUnit, unit));
				
				button4.setActionIcon(Const.imgInteractWorkAt);
				button4.setScript(new interact_WorkAt(gamedata, cityUnit, unit));
			}
			else{
				button0.setActionIcon(Const.imgInteractBuildUpdate);
				button0.setScript(new interact_NodeUpd(gamedata, node, unit));
			}
			
			if(enemy){
				button2.setActionIcon(Const.imgInteractAttack);
				button2.setScript(new interact_Attack(gamedata, node, unit));
				
				button3.setActionIcon(Const.imgInteractTalk);
				button3.setScript(new interact_Talk(gamedata, node, unit));
			}
			
			if(nodeupd){
				button4.setActionIcon(Const.imgInteractWorkAt);
				button4.setScript(new interact_WorkAt(gamedata, nodeupdUnit, unit));
				
				button5.setActionIcon(Const.imgInteractRepair);
				button5.setScript(new interact_Repair(gamedata, node, unit));
			}
		}
		else{
			button0.setActionIcon(Const.imgInteractBuildUpdate);
			button0.setScript(new interact_UpdateBuild(gamedata, node, unit));
		}
		
		int x = Environment.mouseX;
		int y = Environment.mouseY;
		
		if(y + 280 < Environment.frameSizeY){
			button0.setPosition(x, y);
			button1.setPosition(x, y + 50);
			button2.setPosition(x, y + 100);
			button3.setPosition(x, y + 150);
			button4.setPosition(x, y + 200);
			button5.setPosition(x, y + 250);
		}
		else{
			button0.setPosition(x, y - 280);
			button1.setPosition(x, y - 230);
			button2.setPosition(x, y - 180);
			button3.setPosition(x, y - 130);
			button4.setPosition(x, y - 80);
			button5.setPosition(x, y - 30);
		}
		
		button0.setVisible(true);
		button1.setVisible(true);
		button2.setVisible(true);
		button3.setVisible(true);
		button4.setVisible(true);
		button5.setVisible(true);
	}
}
