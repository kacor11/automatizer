package pl.kocjan.automatizer.domain.host.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConnectionDetailsDto {
	private String ip;
	private int port;
}
