package pl.kocjan.automatizer.domain.host;

import java.util.Optional;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.LocalCommandRunner;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.AuthorizeHostDto;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;


public class HostFacade {
	private final HostReader hostReader;
	private final HostCreator hostCreator;
	private final HostMapper hostMapper;
	private final HostAuthorizer hostAuthorizer;
	private final LocalCommandRunner commandRunner;
		
	public HostFacade(HostRepository hostRepository) {
		this.hostReader = new HostReader(hostRepository);
		this.hostMapper = new HostMapper();
		this.commandRunner = new LocalCommandRunner();
		this.hostCreator = new HostCreator(hostRepository, hostMapper);
		this.hostAuthorizer = new HostAuthorizer(hostRepository, commandRunner);
	}
	public Either<Error, Success> createHost(CreateHostDto dto) {
		return hostCreator.createHost(dto);
	}
	
	public Either<Error, Success> authorizeHost(AuthorizeHostDto dto) {
		return hostAuthorizer.authorizeHost(dto);
	}
	
	public Optional<HostDto> readHost(String ip) {
		return hostReader.readHostByIp(ip);
	}
}
