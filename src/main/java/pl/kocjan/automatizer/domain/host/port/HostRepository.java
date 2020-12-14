package pl.kocjan.automatizer.domain.host.port;

import java.util.List;
import java.util.Optional;

import pl.kocjan.automatizer.domain.host.dto.HostDto;

public interface HostRepository {
	
	String saveHost(HostDto host);
	Optional<HostDto> findHostByIp(String ip);
	Optional<List<HostDto>> findHostsByGroup(String group);
}
