package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import misc.Log;

public class Connection implements Runnable {

	protected Socket socket;
    protected ObjectOutputStream out;
    protected ObjectInputStream in;
    
	//new Thread(new Network()).start();
    
	public Connection(String ip, int port) throws IOException {
		
		InetAddress address = InetAddress.getByName(ip);
		
		try {
			socket = new Socket(address, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch(ConnectException e){
			Log.err("No servers found");
		}
	}

	@Override
	public void run() {
		Log.debug("Connection sucess " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
		
		try {
			while(socket.isBound()){
				Object obj = in.readObject();
				
				if(obj != null){
					Message msg = (Message)obj;
					Log.debug("MSG:" + msg.timestamp + ":" + msg.prefix + "." + msg.data);
				}
        	}
		}
		catch (IOException e) {
			Log.err("Disconnected by server");
		} 
		catch (ClassNotFoundException e) {
			Log.err(("Currupted message from " + socket.getLocalAddress() + " ID: " + socket.getPort()));
		}
	}

	public void send(Message msg) throws IOException{
		out.writeObject(msg);
	}
}
