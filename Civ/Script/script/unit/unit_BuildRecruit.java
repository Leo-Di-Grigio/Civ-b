package script.unit;

import java.io.IOException;

import database.ConstAction;
import database.DB;
import net.Message;
import net.Message.Prefix;
import network.Network;
import misc.Log;
import scenedata.game.GameData;
import script.gui.ScriptGui;
import tasks.Task;

public class unit_BuildRecruit extends ScriptGui {

	private int unitId; 
	
	public unit_BuildRecruit(GameData gamedata, int unitId) {
		this.unitId = unitId;
	}
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute unit_BuildRecruit by unitId: " + this.unitId);
		// msg.data = "ConstAction:unitId:unitType"
		Network.sendMsg(new Message(Prefix.PLAYER_ACTION, "" + ConstAction.cityBuildUnit + ":" + this.unitId + ":" + DB.unitRecruit));
	}
}
