package pl.kocjan.automatizer.app.server;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;
	
	public Message(String message) {
		this.msg = message;
	}
	
	public String getMessage() {
		return msg;
	}
}
