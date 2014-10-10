package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import misc.Const;

public class Server implements Runnable {
	
	protected static ServerSocket server;
	protected static ClientPool clients;
	
	public Server() throws IOException {
		server = new ServerSocket(Const.port);
		System.out.println(Const.title + " v" + Const.version + "." + Const.subVersion + " is runned");
	}

	@Override
	public void run() {
		try {
			while(true) {
            	Socket socket = server.accept();
            	System.err.println("Client accepted " + socket.getInetAddress() + " ID: " + socket.getPort());
            	
            	Client client = new Client(socket);
            	clients.add(client);
        	}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
