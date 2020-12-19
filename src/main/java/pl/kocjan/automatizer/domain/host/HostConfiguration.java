package pl.kocjan.automatizer.domain.host;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.kocjan.automatizer.domain.host.port.HostRepository;

@Configuration
public class HostConfiguration {

	@Bean
	HostFacade hostFacade(HostRepository hostRepository) {
		return new HostFacade(hostRepository);
	}
}
