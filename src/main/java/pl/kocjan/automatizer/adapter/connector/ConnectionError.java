package pl.kocjan.automatizer.adapter.connector;

import lombok.AllArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;

@AllArgsConstructor
public enum ConnectionError implements Error {
	KNOWN_HOSTS_ERROR("Can't locate known hosts file"),
	HOST_CONNECTION_ERROR("Can't connect to the host"),
	HOST_EXECUTION_ERROR("Running command on remote host failed");
	
	private final String error;
	
	@Override
	public String getError() {
		return this.error;
	}

}
