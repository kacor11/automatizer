package pl.kocjan.automatizer.domain.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.kocjan.automatizer.domain.user.port.UserRepository;

@Configuration
public class UserConfiguration {

	@Bean
	UserFacade userFacade(UserRepository userRepository) {
		return new UserFacade(userRepository);
	}
}
