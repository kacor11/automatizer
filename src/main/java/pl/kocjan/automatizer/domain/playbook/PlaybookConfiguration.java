package pl.kocjan.automatizer.domain.playbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.host.port.HostRepository;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;

@Configuration
public class PlaybookConfiguration {

	@Bean
	PlaybookFacade playbookFacade(HostRepository hostRepository, HostFacade hostFacade, HostConnection hostConnection) {
		return new PlaybookFacade(hostRepository, hostFacade, hostConnection);
	}
}
