package scene.game;

import java.io.IOException;

import database.ConstAction;
import net.Message;
import net.Message.Prefix;
import network.Network;
import misc.Log;
import player.units.Unit;
import scenedata.game.GameData;
import script.gui.ScriptGui;

public class interact_Attack extends ScriptGui {

	public interact_Attack(GameData gamedata, int nodeX, int nodeY, Unit unit) throws IOException {
		Log.debug("Execute interact_Attack");
		String data = "" + ConstAction.interact + ":" + ConstAction.interactAttack + ":" + unit.id + ":" + nodeX + ":" + nodeY;
		Message msg = new Message(Prefix.PLAYER_ACTION, data);
		Network.sendMsg(msg);
	}
}
