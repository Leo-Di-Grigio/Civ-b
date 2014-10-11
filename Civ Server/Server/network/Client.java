package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
	
	// id
	private static int ID = 0;
	protected int id;
	
	// socket
    protected Socket socket;
    protected ObjectInputStream in;
    protected ObjectOutputStream out;
	
	public Client(Socket socket) throws IOException {
		this.id = ID++;
		this.socket = socket;
		
		this.in = new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
	}

	public int getId(){
		return id;
	}
	
	@Override
	public void run() {
		try {
			while(socket.isBound()){
				Object obj = in.readObject();
				
				if(obj != null){
					Message msg = (Message)obj;
					System.out.println("Client ID:" + id + ":"  + msg.timestamp + ":" + msg.prefix + "." + msg.data);
				}
			}
		}
		catch (IOException e) {
			System.out.println("Disconnected " + socket.getLocalAddress() + " ID: " + socket.getPort());
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Currupted message from " + socket.getLocalAddress() + " ID: " + socket.getPort());
		}
		finally {
			System.out.println("Finalize connection");
		}
	}
	
	public void send(Message msg) throws IOException{
		out.writeObject(msg);
		//out.flush();
	}
}
