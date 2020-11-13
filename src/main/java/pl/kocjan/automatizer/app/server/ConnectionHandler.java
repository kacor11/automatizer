package pl.kocjan.automatizer.app.server;

import java.util.ArrayList;
import java.util.List;

public class ConnectionHandler {
	private List<SocketConnection> sockets;
	
	ConnectionHandler() {
		this.sockets = new ArrayList<>();
	}
	
	void registerConnection(SocketConnection worker) {
		sockets.add(worker);
	}
	
	void printConnections() {
		sockets.forEach(System.out::println);
	}
	
	void printStatus() {
		sockets.forEach(socket -> System.out.println("Socket is " + socket.isAlive()));
	}
}
