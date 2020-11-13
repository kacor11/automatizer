package pl.kocjan.automatizer.app.server;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionHandler handler = new ConnectionHandler();
		Server server = new Server(7777, 100, handler);
		Thread thread = new Thread(server);
		thread.start();
		System.out.println("Server started");
		System.out.println(Thread.currentThread());

	}

}
