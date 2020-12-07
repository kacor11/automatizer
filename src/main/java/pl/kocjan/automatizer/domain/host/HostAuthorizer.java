package pl.kocjan.automatizer.domain.host;

import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import pl.kocjan.automatizer.domain.common.LocalCommandRunner;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.AuthorizeHostDto;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.dto.HostError;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

@AllArgsConstructor
class HostAuthorizer {
	private final HostRepository hostRepository;
	private final LocalCommandRunner commandRunner;
	private final HostMapper hostMapper;

	public Either<Error, Success> authorizeHost(AuthorizeHostDto authorizeHostDto) {
		return findHost(authorizeHostDto)
		.flatMap(hostDto -> executeAuthorizationCommand(authorizeHostDto, hostDto))
		.flatMap(this::updateHost);
	}		
	
	private Either<Error, Host> executeAuthorizationCommand(AuthorizeHostDto dto, HostDto hostDto) {
		String sshAuthorizeCommand = commandRunner.createSshCopyCommand(dto.getSshPassword(), dto.getUsername(), dto.getIp());
		return commandRunner.processCommand(sshAuthorizeCommand).map(succes -> hostMapper.dtoToHost(hostDto));
	}

	private Either<Error, HostDto> findHost (AuthorizeHostDto authorizeHostDto) {
		return Option.ofOptional(hostRepository.findHostByIp(authorizeHostDto.getIp())).toEither(HostError.HOST_DOESNT_EXIST);
	}
	
	private Either<Error, Success> updateHost(Host host) {
		host.authorize();
		return Try.of(() -> updateHostInRepository(host))
				.toEither(HostError.DATABASE_ERROR);
	}
	
	private Success updateHostInRepository(Host host) {
		hostRepository.saveHost(hostMapper.hostToDto(host));
		return new Success();
	}
	
}

