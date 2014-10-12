package script.network;

import java.io.IOException;

import misc.Log;
import network.Message;
import network.Network;


public class net_ReciveMsg extends ScriptNetwork {

	public static void execute(Message msg) throws IOException {
		
		switch(msg.prefix){
		
			// normal connection
			case "connection_ok":{
				Log.debug("Ok, connection is valid");
				Network.sendMsg(new Message("requestGamesStatus", null));
			} break;
			
			// connection err, not valid game version
			case "connection_err":{
				Log.err("Not valid client version, need v" + msg.data);
				Network.sendMsg(new Message("disconnect", null));
			} break;
			
			// games list in Hub
			case "gamesList":{
				Log.debug("GameList:\n" + msg.data);
			} break;
			
			case "debug":{
				Log.debug("MSG: " + msg.prefix + ":" + msg.data);
			} break;
		}
	}
}
