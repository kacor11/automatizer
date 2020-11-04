package pl.kocjan.automatizer.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable  {
			
		private int port;
		private ServerSocket serverSocket;

		
		public Server(int port) {
			this.port = port;		
	}
		public void run() {
			try {
				serverSocket = new ServerSocket(port);
				System.out.println("Created socket");
				System.out.println("Server Running on " + Thread.currentThread());
				while(true) {
					RunnableWorker worker = new RunnableWorker(serverSocket.accept());
					Thread thread = new Thread(worker);
					thread.start();
					System.out.println("Connection accepted");					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
	