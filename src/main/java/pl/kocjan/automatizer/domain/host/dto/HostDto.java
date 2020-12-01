package pl.kocjan.automatizer.domain.host.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HostDto {
	private final Long id;
	private final String ip;
	private final int port;
	private final Set<String> groups;
	private boolean isAuthorized;	
}
