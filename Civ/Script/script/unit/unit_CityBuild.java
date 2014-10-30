package script.unit;

import java.io.IOException;

import database.ConstAction;
import net.Message;
import net.Message.Prefix;
import network.Network;
import misc.Log;
import scenedata.game.GameData;
import script.gui.ScriptGui;
import tasks.Task;

public class unit_CityBuild extends ScriptGui {

	private int unitId; 
	
	public unit_CityBuild(GameData gamedata, int unitId) {
		this.unitId = unitId;
	}

	@Override
	public void execute(Task task) throws IOException {
		Log.debug("Execute unit_CityBuild by unitId: " + this.unitId);
		Network.sendMsg(new Message(Prefix.PLAYER_ACTION, "" + ConstAction.cityBuild + ":" + this.unitId));
	}
}
