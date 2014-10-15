package script.gui.button;

import java.io.IOException;

import gui.GUI;
import gui.elements.GuiElementGamesList;
import net.Message;
import net.Message.Prefix;
import network.Network;
import main.Config;
import misc.Log;
import misc.TableLine;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_JoinTheGame extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException{
		Log.debug("Execute gui_button_JoinTheGame");
		
		GUI gui = (GUI)task.dataPost;
		
		if(gui != null){
			GuiElementGamesList list = (GuiElementGamesList)gui.get("gameslist");
			
			if(list != null){
				TableLine line = list.getSelectedLine();
				
				if(line != null){
					String id = line.getCell(0);
					
					if(id != null){
						Network.sendMsg(new Message(Prefix.REQ_GAME_JOIN, id + ":" + Config.playerName));
					}
				}
			}
		}
	}
}
