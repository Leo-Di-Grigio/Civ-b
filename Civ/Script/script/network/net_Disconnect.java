package script.network;

import java.io.IOException;

import network.Network;

public class net_Disconnect extends ScriptNetwork {
	
	public static void execute() throws IOException{
		Network.disconnect();
	}
}
