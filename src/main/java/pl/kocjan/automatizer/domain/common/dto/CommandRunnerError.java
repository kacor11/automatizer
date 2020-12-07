package pl.kocjan.automatizer.domain.common.dto;

import lombok.AllArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;

@AllArgsConstructor
public enum CommandRunnerError implements Error {	
	COMMAND_EXECUTION_ERROR("Can't execute specific command"),
	INCORRECT_SSH_PASSWORD("Ssh password you provided is incorrect");
	
	private final String error;
	
	@Override
	public String getError() {
		return this.error;
	}

}
