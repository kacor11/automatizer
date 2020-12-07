package pl.kocjan.automatizer.domain.host.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateHostDto {
	private String ip;
	private String username;
	private int port;
	private Set<String> groups;	
}
