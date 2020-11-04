package pl.kocjan.automatizer.app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket socket = new Socket("localhost", 7777);
		System.out.println("Socket created");
		
		Scanner scanner = new Scanner(System.in);
		
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		System.out.println("Wpisuj");
		String line = "";
		while(!line.equals("koniec")) {
			line = scanner.nextLine();
			System.out.println(line);
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
		System.out.println("zamykam");
		scanner.close();
		System.out.println("Msg sent");
		bufferedWriter.close();
		socket.close();
	}

}
