package network;

import game.GamesMng;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import task.TaskPool;
import misc.Const;
import misc.ToolsConst;
import misc.Log;

public class Server implements Runnable {
	
	protected static ServerSocket server;
	
	public Server() throws IOException {
		Log.msg(Const.title + " v" + Const.version + "." + Const.subVersion);
		Log.msg(ToolsConst.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion);
		
		try {
			server = new ServerSocket(Const.port);
		}
		catch(BindException e){
			Log.err("Another server is already bind port: " + Const.port);
			System.in.read();
			System.exit(0);
		}
		
		new ClientPool();
		new TaskPool();
		new GamesMng();
		
		Log.service("Server launched");
	}

	@Override
	public void run() {
		try {
			while(server.isBound()) {
            	Socket socket = server.accept();
            	Log.service("Client accepted " + socket.getInetAddress() + " ID: " + socket.getPort());
            	
            	Client client = new Client(socket);
            	ClientPool.add(client);
        	}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
