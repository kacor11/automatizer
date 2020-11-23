package pl.kocjan.automatizer.domain.host.dto;

import lombok.AllArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;

@AllArgsConstructor
public enum HostError implements Error {
	HOST_ALREADY_EXISTS("Host with this addres already exists");
	
	private final String error;

	
	@Override
	public String getCause() {		
		return this.error;
	}

}
