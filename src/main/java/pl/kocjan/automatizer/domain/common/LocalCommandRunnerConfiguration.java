package pl.kocjan.automatizer.domain.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalCommandRunnerConfiguration {

	@Bean
	LocalCommandRunner localCommandRunner() {
		return new LocalCommandRunner();
	}
}
