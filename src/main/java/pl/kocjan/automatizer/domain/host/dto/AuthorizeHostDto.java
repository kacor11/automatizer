package pl.kocjan.automatizer.domain.host.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthorizeHostDto {
	private String ip;
	private String sshPassword;
	private int port;
}
