package pl.kocjan.automatizer.domain.host.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateHostDto {
	private String ip;
	private int port;
	private String username;
	private String password;
	
}
