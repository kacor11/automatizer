package pl.kocjan.automatizer.domain.host;

import java.util.Optional;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;


public class HostFacade {
	private final HostReader hostReader;
	private final HostCreator hostCreator;
	private final HostMapper hostMapper;
		
	public HostFacade(HostRepository hostRepository) {
		this.hostReader = new HostReader(hostRepository);
		this.hostMapper = new HostMapper();
		this.hostCreator = new HostCreator(hostRepository, hostMapper);
	}
	public Either<Error, Success> createHost(CreateHostDto dto) {
		return hostCreator.createHost(dto);
	}
	
	public Optional<HostDto> readHost(String ip) {
		return hostReader.readHostByIp(ip);
	}
}
