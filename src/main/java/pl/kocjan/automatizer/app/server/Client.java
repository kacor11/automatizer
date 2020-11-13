package pl.kocjan.automatizer.app.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket socket = new Socket("localhost", 7777);
		System.out.println("Socket created");
		
		
		ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
		
		Runnable run = new Runnable() {
			public void run() {
				try {
					Thread.sleep(5000);
					Message msg1 = new Message("Pierwsza wiadomosc");
					System.out.println("Wyslam pierwsza");
					writer.writeObject(msg1);
					writer.flush();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		new Thread(run).start();

	}

}
