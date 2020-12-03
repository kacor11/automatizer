package pl.kocjan.automatizer.domain.host;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;

@Builder
@Getter
public class Host {
	private Long id;
	private String ip;
	private int port;
	private boolean isAuthorized;
	private Set<String> groups;
	
	static Host buildHost(CreateHostDto dto) {
				return Host.builder()
						.ip(dto.getIp())
						.port(dto.getPort())
						.groups(Set.of("Default"))
						.isAuthorized(false)
						.build();								
	}
	
		
	void authorize() {
		this.isAuthorized = true;
	}
}
