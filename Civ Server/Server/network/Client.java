package network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import task.Task;
import task.TaskPool;
import net.Message;
import net.Message.Prefix;
import misc.Log;

public class Client implements Runnable {
	
	// id
	private static int ID = 0;
	protected int clientId;
	
	// socket
    protected Socket socket;
    protected ObjectInputStream in;
    protected ObjectOutputStream out;
    
    // game id
    public int gameId = -1;
	
	public Client(Socket socket) throws IOException {
		this.clientId = ID++;
		this.socket = socket;
		
		this.in = new ObjectInputStream(socket.getInputStream());
		this.out = new ObjectOutputStream(socket.getOutputStream());
	}

	public int getClientId(){
		return clientId;
	}
	
	public void setGameId(int gameId){
		this.gameId = gameId;
	}
	
	@Override
	public void run() {
		try {
			while(socket.isBound()){
				Object obj = in.readObject();
				
				if(obj != null){
					Message msg = (Message)obj;
					Log.service("<- ID: "+clientId+":"+msg.prefix +":"+msg.data);
					TaskPool.add(new Task(clientId, msg));
				}
			}
		}
		catch (IOException e) {
			Log.service("Disconnected " + socket.getLocalAddress() + " ID: " + socket.getPort());
		} 
		catch (ClassNotFoundException e) {
			Log.err("Currupted message from " + socket.getLocalAddress() + " ID: " + socket.getPort());
		} 
		catch (Throwable e) {
			e.printStackTrace();
		}
		finally {
			try {
				TaskPool.add(new Task(clientId, new Message(Prefix.REQ_DISCONNECT, null)));
				Log.debug("Finalize connection");
			} 
			catch (IOException e) {
				e.printStackTrace();
				Log.err("Finalize connection clientId: " + clientId);
			} 
			catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public void disconnect() throws Throwable {
		this.finalize();
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.in.close();
		this.out.close();
		this.socket.close();
		super.finalize();
	}
	
	public void send(Message msg) throws IOException{
		Log.service("-> ID: "+clientId+":"+msg.prefix +":"+msg.data);
		out.writeObject(msg);
	}
}
