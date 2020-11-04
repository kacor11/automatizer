package pl.kocjan.automatizer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import pl.kocjan.automatizer.app.CommandRunner;
import pl.kocjan.automatizer.app.LinuxCommandRunner;

@SpringBootApplication
@ComponentScan("pl.kocjan")
public class AutomatizerApplication {

	public static void main(String[] args) {	
		ApplicationContext ctx = SpringApplication.run(AutomatizerApplication.class, args);
		CommandRunner commandRunner = (LinuxCommandRunner) ctx.getBean("linuxCommandRunner");
		try {
			commandRunner.createUser("Stefan1", "7675277");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
