package pl.kocjan.automatizer.domain.host;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class HostAuthorizer {
	
	HostHealthChecker healthChecker;
	
	Either<Error, Success> authorizeHost
}
