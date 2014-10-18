package scene.prepare;

import gui.GUI;
import gui.elements.GuiElementTable;
import gui.misc.TableLine;

import java.io.IOException;

import misc.Log;
import net.Message;
import net.Message.Prefix;
import network.Network;
import script.gui.ScriptGui;
import tasks.Task;

public class gui_button_JoinTeam extends ScriptGui {
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute gui_button_JoinTeam");
		GUI gui = (GUI)task.dataPost;
		
		if(gui != null){
			GuiElementTable table = (GuiElementTable)gui.get("players");
			
			TableLine line = table.getSelectedLine();
			
			if(line != null){
				String str = line.getCell(0);
			
				if(str != null && str.compareTo("") != 0){
					int	id = Integer.parseInt(str); 
					Network.sendMsg(new Message(Prefix.REQ_TEAM_CHOOSE, ""+id));
				}
			}
		}
	}
}
