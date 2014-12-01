package script.unit;

import java.io.IOException;

import net.Message;
import net.Message.Prefix;
import network.Network;
import database.ConstAction;
import misc.Log;
import scenedata.game.GameData;
import script.gui.ScriptGui;
import tasks.Task;

public class unit_Mine extends ScriptGui {
	
	private int unitId;
	
	public unit_Mine(GameData gamedata, int unitId) {
		this.unitId = unitId;
	}
	
	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute unit_Mine by unitId: " + this.unitId);
		Network.sendMsg(new Message(Prefix.PLAYER_ACTION, "" + ConstAction.mine + ":" + this.unitId));
	}
}
