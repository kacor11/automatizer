package pl.kocjan.automatizer.app.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable  {
			
		private ServerSocket serverSocket;
		private final ConnectionHandler handler;
		private final int port;
		private final ExecutorService pool;

		
		public Server(int port, int poolSize, ConnectionHandler handler) {
			this.port = port;		
			this.handler = handler;
			this.pool = Executors.newFixedThreadPool(poolSize);
	}
		public void run() {
			try {
				serverSocket = new ServerSocket(port);
				System.out.println("Created socket");
				System.out.println("Server Running on " + Thread.currentThread());
				while(true) {
					Socket socket = serverSocket.accept();
					SocketConnection connection = new SocketConnection(socket);
					pool.execute(connection);
					registerConnection(connection);
					handler.printStatus();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		private void registerConnection(SocketConnection connection) {
			handler.registerConnection(connection);
		}
		
}
	