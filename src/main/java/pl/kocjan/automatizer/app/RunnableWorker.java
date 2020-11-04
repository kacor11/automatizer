package pl.kocjan.automatizer.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class RunnableWorker implements Runnable {
	
	private Socket socket;
	private InputStream input;
	private OutputStream output;
	
	public RunnableWorker(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			this.input = socket.getInputStream();
			this.output = socket.getOutputStream();
			System.out.println("Worker Running on " + Thread.currentThread());
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
