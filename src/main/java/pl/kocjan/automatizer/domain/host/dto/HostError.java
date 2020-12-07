package pl.kocjan.automatizer.domain.host.dto;

import lombok.AllArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;

@AllArgsConstructor
public enum HostError implements Error {
	HOST_ALREADY_EXISTS("Host with this addres already exists"),
	ESTABLISHING_CONNECTION_FAILED("Can't establish connection with specific host"),
	DATABASE_ERROR("Host already in database"),
	HOST_DOESNT_EXIST("Can't find host with specific ip");
	
	
	private final String error;
	
	@Override
	public String getError() {		
		return this.error;
	}

}
