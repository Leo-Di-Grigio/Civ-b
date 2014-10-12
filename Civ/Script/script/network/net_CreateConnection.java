package script.network;

import java.io.IOException;

import main.Config;
import misc.Log;
import network.Network;

public class net_CreateConnection extends ScriptNetwork {

	public static void execute() throws IOException{
		Log.debug("Execute net_CreateConnection");
		Network.createConnection(Config.serverAddress, Config.serverPort);
	}
}
