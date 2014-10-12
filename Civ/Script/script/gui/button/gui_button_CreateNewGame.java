package script.gui.button;

import gui.GUI;

import gui.elements.GuiElementGamesList;

import java.io.IOException;

import network.Message;
import network.Network;
import network.Message.Prefix;
import misc.Log;
import misc.TableLine;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_CreateNewGame extends ScriptGui {

	@Override
	public void execute(Task task) throws IOException{
		Log.debug("Execute gui_button_CreateNewGame");
		
		GUI gui = (GUI)task.dataPost;
		
		if(gui != null){
			GuiElementGamesList list = (GuiElementGamesList)gui.get("gameslist");
			
			if(list != null){
				TableLine line = list.getSelectedLine();
				
				if(line != null){
					String id = line.getCell(0);
					
					if(id != null){
						Network.sendMsg(new Message(Prefix.REQUEST_GAME_CONNECTION, id));
					}
				}
			}
		}
	}
}
