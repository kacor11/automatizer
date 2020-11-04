package pl.kocjan.automatizer.app;

import java.io.IOException;

public interface CommandRunner {
	public void createUser(String name);
	public void createUser(String name, String password) throws IOException, InterruptedException;
	public void removeUser(String name);
}
