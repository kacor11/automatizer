package pl.kocjan.automatizer.domain.host;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

@RequiredArgsConstructor
class HostReader {
	private final HostRepository hostRepository;
	
	Optional<HostDto> readHostByIp(String ip) {
		return hostRepository.findHostByIp(ip);
	}
	
	Optional<List<HostDto>> getHostsWithGroup(String group) {
		return hostRepository.findHostsByGroup(group);
	}
}
