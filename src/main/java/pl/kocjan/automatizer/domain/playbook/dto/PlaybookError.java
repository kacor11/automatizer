package pl.kocjan.automatizer.domain.playbook.dto;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;

@RequiredArgsConstructor
public enum PlaybookError implements Error {
	HOSTS_GROUP_ERROR("No hosts to run this playbook on");

	private final String error;
	@Override
	public String getError() {
		return this.error;
	}
	
}
