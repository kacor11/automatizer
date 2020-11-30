package pl.kocjan.automatizer.domain.host;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Set;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.Builder;
import lombok.Getter;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;
import pl.kocjan.automatizer.domain.host.dto.HostError;

@Builder
@Getter
public class Host {
	private String ip;
	private int port;
	private boolean isAuthorized;
	private Set<String> groups;
	
	static Host createHost(CreateHostDto dto) {
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
