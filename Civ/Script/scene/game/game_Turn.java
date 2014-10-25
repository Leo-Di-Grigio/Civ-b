package scene.game;

import gui.GUI;
import gui.elements.GuiElementButton;

import java.io.IOException;

import painter.Painter;
import net.Message;
import net.Message.Prefix;
import network.Network;
import misc.Enums;
import misc.Log;
import scenedata.game.GameData;
import script.gui.ScriptGui;
import tasks.Task;

public class game_Turn extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute game_EndTurn");
		
		GuiElementButton button = (GuiElementButton)task.sceneGui.get(scenegui_Game.uiButtonEndTurn);
		button.setVisible(false);
		
		Painter.addTask(new Task(Enums.Task.GAME_TURN_END, null));
		Network.sendMsg(new Message(Prefix.GAME_TURN_END, null));
	}

	public static void nextTurn(GUI gui, GameData gamedata) {
		Log.debug("Execute game_NextTurn");
		
		GuiElementButton button = (GuiElementButton)gui.get(scenegui_Game.uiButtonEndTurn);
		
		if(gamedata.turn){
			button.setVisible(true);
		}
		else{
			button.setVisible(false);
		}
	}
}
