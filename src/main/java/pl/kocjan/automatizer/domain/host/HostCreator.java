package pl.kocjan.automatizer.domain.host;

import java.util.Optional;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;
import pl.kocjan.automatizer.domain.host.dto.HostError;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

@RequiredArgsConstructor
class HostCreator {
	
	private final HostRepository hostRepository;
	private final HostMapper hostMapper;
		
	Either <Error, Success> createHost(CreateHostDto createHostDto) {
		Optional<Error> validationError = validateHostData(createHostDto);
		
		return validationError.isPresent() ? Either.left(validationError.get()) : create(createHostDto);
	}
	
	private Either<Error, Success> create(CreateHostDto crateHostDto) {
		Host host = Host.buildHost(crateHostDto);
		return saveHost(host);
				
	}
		
	private Either<Error, Success> saveHost(Host host) {
		return Try.of(() -> saveHostInRepository(host))
				.toEither(HostError.DATABASE_ERROR);
	}
	
	private Success saveHostInRepository(Host host) {
		hostRepository.saveHost(hostMapper.hostToDto(host));
		return new Success();
	}
	
	private Optional<Error> validateHostData(CreateHostDto createHostDto) {
		return hostRepository.findHostByIp(createHostDto.getIp()).isPresent() ? 
				Optional.of(HostError.HOST_ALREADY_EXISTS) : Optional.empty();
	}
	
}

