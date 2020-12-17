package pl.kocjan.automatizer.domain.host;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.HostError;
import pl.kocjan.automatizer.domain.host.port.HostRepository;


@RequiredArgsConstructor
public class HostUpdater {
	
	private final HostMapper hostMapper;
	private final HostRepository hostRepository;
	
	
	Either<Error, Success> saveHost(Host host) {
		return Try.of(() -> saveHostInRepository(host))
				.toEither(HostError.DATABASE_ERROR);
	}
	
	private Success saveHostInRepository(Host host) {
		hostRepository.saveHost(hostMapper.hostToDto(host));
		return new Success();
	}
}
