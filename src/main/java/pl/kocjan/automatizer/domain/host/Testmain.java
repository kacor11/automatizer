package pl.kocjan.automatizer.domain.host;

import java.net.InetSocketAddress;
import java.net.Socket;

import io.vavr.control.Try;

public class Testmain {

	public static void main(String[] args) {
		System.out.println(checkHostAvailability("asdfghj", 22, 1000));

	}
	
	private static boolean checkHostAvailability(String ip, int port, int timeout) {
		
		return Try.withResources(() -> new Socket())
			.of(socket -> {
				socket.connect(new InetSocketAddress(ip, port), timeout);
				return true;
			})
			.isSuccess();
	}

}
