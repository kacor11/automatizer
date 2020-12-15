package pl.kocjan.automatizer.domain.host;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

@RequiredArgsConstructor
class HostReader {
	
	private final HostMapper mapper;
	private final HostRepository hostRepository;
	
	Optional<HostDto> readHostByIp(String ip) {
		return hostRepository.findHostByIp(ip);
	}
	
	List<Host> getHostsWithGroup(String group) {
		return hostRepository.findHostsByGroup(group)
				.stream()
				.map(host -> mapper.dtoToHost(host))
				.collect(Collectors.toList());
	}
}
