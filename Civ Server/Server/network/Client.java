package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import misc.Log;

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
					Log.service("<- ID: "+id+":"+msg.prefix +":"+msg.data);
					TaskPool.add(new Task(id, msg));
				}
			}
		}
		catch (IOException e) {
			Log.service("Disconnected " + socket.getLocalAddress() + " ID: " + socket.getPort());
		} 
		catch (ClassNotFoundException e) {
			Log.err("Currupted message from " + socket.getLocalAddress() + " ID: " + socket.getPort());
		}
		finally {
			try {
				ClientPool.remove(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.gc();
			Log.debug("Finalize connection");
		}
	}
	
	public void send(Message msg) throws IOException{
		Log.service("-> ID: "+id+":"+msg.prefix +":"+msg.data);
		out.writeObject(msg);
	}
}
