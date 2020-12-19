package pl.kocjan.automatizer.adapter.connector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfiguration {

	@Bean
	SshHostConnection sshHostConnection() {
		return new SshHostConnection();
	}
}
