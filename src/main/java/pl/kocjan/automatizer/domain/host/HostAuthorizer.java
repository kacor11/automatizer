package pl.kocjan.automatizer.domain.host;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import pl.kocjan.automatizer.domain.common.LocalCommandRunner;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.AuthorizeHostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

@AllArgsConstructor
class HostAuthorizer {
	private final HostRepository hostRepository;
	private final LocalCommandRunner commandRunner;

	public Either<Error, Success> authorizeHost(AuthorizeHostDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
}

