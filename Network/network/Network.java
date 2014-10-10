package network;

import networkapi.NetworkAPI;
import misc.Log;

public class Network {

	protected NetworkAPI netApi;
	
	public Network() {
		netApi = new NetworkAPI();
		
		Log.msg("Network loaded");
	}
}
