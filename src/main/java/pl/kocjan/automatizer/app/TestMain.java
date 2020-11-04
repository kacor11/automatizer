package pl.kocjan.automatizer.app;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server(7777);
		Thread thread = new Thread(server);
		thread.start();
		System.out.println("Server started");
		System.out.println(Thread.currentThread());

	}

}
