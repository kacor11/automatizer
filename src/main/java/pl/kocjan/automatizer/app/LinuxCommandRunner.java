package pl.kocjan.automatizer.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.springframework.stereotype.Component;

@Component
public class LinuxCommandRunner implements CommandRunner {
	
	@Override
	public void createUser(String name, String password) throws IOException, InterruptedException {
		
        ProcessBuilder pb = new ProcessBuilder("ssh-copy-id", "kacor11@localhost");
        pb.redirectErrorStream(true);		
		Process process = pb.start();
		
		try (
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			) {
			writer.write(password + "\n");
			writer.flush();
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				if (line.contains("Sorry")) {
					writer.write(password);
					writer.newLine();
					writer.flush();
				}
			}
			int exitValue = process.waitFor();
			System.out.println("ExitValue = " + exitValue);
		}
	}

	@Override
	public void removeUser(String name) {
		// TODO Auto-generated method stub
		
	}
	
	private void runAsRoot(String rootPassword) {
		
	}

	@Override
	public void createUser(String name) {
		// TODO Auto-generated method stub
		
	}
}
