package pl.kocjan.automatizer.host;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Host {
	private String ip;
	private int port;
	
}
