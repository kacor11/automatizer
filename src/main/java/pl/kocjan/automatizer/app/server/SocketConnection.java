package pl.kocjan.automatizer.app.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class SocketConnection implements Runnable {
	
	enum Mode {
		READ,
		WRITE,
		AWAIT
	}
	
	private Socket socket;
	private boolean isAlive;
	private Mode mode;
	
	SocketConnection(Socket socket) {
		this.socket = socket;
		this.isAlive = true;
		this.mode = Mode.WRITE;
	}
	
	public void run() {
		try {		
			ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
			while(true) {
				if(mode.equals(Mode.AWAIT)) {
					System.out.println(reader.readObject().getClass());
					this.wait();
					mode = Mode.READ;
				}
				System.out.println("In mode read");
				reader.readObject();
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean getOutput() {
		return socket.isConnected();
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
}
