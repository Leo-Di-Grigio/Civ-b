package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	
	protected static final int PORT = 6600;
	protected static ServerSocket server;
	
	public static void main(String [] args) throws IOException {
		runServer();
	}
	
	private static void runServer() throws IOException{
		server = new ServerSocket(PORT);
		System.out.println("Server runed");
        
        while(true) {
            Socket socket = server.accept();
            System.err.println("Client accepted " + socket.getInetAddress() + " ID: " + socket.getPort());
            new Thread(new Client(socket)).start();
        }
	}
}
