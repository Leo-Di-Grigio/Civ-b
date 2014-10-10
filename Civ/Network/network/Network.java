package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import networkapi.NetworkAPI;
import misc.Log;

public class Network implements Runnable {

	protected NetworkAPI netApi;
	
	protected static final int PORT = 6600;
	protected static final String ADDRESS = "127.0.0.1";
	
	protected Socket socket;
    protected OutputStream out;
    protected InputStream in;
	
	public Network() throws IOException {
		netApi = new NetworkAPI();
		InetAddress address = InetAddress.getByName(ADDRESS);
		
		try {
			socket = new Socket(address, PORT);
			in = socket.getInputStream();
        	out = socket.getOutputStream();
        	this.run();
		}
		catch(ConnectException e){
			Log.err("No servers found");
		}
	}
	
	public void send(int num) throws IOException{
		out.write(num);
	}

	@Override
	public void run() {
		Log.debug("Network loaded");
		try {
			while(true){
				int num = in.read();
				System.out.println("" + num);
        	}
		}
		catch (IOException e) {
			Log.err("Disconnected by server");
		}
	}
}
