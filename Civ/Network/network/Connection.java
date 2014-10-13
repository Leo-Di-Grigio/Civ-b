package network;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import network.Message.Prefix;
import painter.Painter;
import tasks.Task;
import misc.Const;
import misc.Enums;
import misc.Log;

public class Connection implements Runnable {

	protected Socket socket;
    protected ObjectOutputStream out;
    protected ObjectInputStream in;
    
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
		if(socket == null){
			Log.debug("No connection avaible");
		}
		else{
			Log.debug("Connection sucess " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
			Log.debug("Begin client version check...");
			// send client-server version
			try {
				this.send(new Message(Prefix.CHECK_VERSION, ""+Const.version+"."+Const.subVersion));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		
			try {
				while(socket.isBound()){
					Object obj = in.readObject();
				
					if(obj != null){
						Message msg = (Message)obj;
						Log.debug("NET_MSG_RECIVE:" + msg.prefix+ ":" + msg.data);
						Painter.currentScene.addTask(new Task(Enums.Task.NETWORK_MESSAGE_READ, msg));
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
	}

	public void send(Message msg) throws IOException{
		Log.debug("NET_MSG:" + msg.prefix+ ":" + msg.data);
		out.writeObject(msg);
	}
}
