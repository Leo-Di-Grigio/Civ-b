package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client implements Runnable {
	
    protected Socket socket;
    protected InputStream in;
    protected OutputStream out;
	
	public Client(Socket socket) throws IOException {
		this.socket = socket;
		
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
	}

	@Override
	public void run() {

		try {
			while(true){
				int num = in.read();
				System.out.println(num);
			}
		}
		catch (IOException e) {
			System.out.println("Disconnected " + socket.getLocalAddress() + " ID: " + socket.getPort());
		}
	}
	
	public void send(int num) throws IOException{
		out.write(num);
	}
}
