package network;

import game.Game;
import game.GameList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import misc.Const;
import misc.ToolsConst;
import misc.Log;

public class Server implements Runnable {
	
	protected static ServerSocket server;
	
	public Server() throws IOException {
		
		new ClientPool();
		new TaskPool();
		new GameList();
		
		// test
		GameList.add(new Game("Test1", Const.mapSizeX, Const.mapSizeY, Const.playersMax));
		GameList.add(new Game("Test2", Const.mapSizeX, Const.mapSizeY, Const.playersMax));
		
		server = new ServerSocket(Const.port);
		Log.msg(Const.title + " v" + Const.version + "." + Const.subVersion + " is runned");
		Log.msg(ToolsConst.title + " v" + ToolsConst.version + "." + ToolsConst.subVersion);
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
