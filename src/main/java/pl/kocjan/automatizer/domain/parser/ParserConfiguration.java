package pl.kocjan.automatizer.domain.parser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfiguration {
	@Bean
	YamlParser yamlParser() {
		return new YamlParser();
	}
}
