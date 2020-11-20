package pl.kocjan.automatizer.Host.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateHostDto {
	private String ip;
	private int port;
	
}
