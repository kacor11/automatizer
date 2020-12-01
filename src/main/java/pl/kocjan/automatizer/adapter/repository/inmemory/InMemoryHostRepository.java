package pl.kocjan.automatizer.adapter.repository.inmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

@RequiredArgsConstructor
public class InMemoryHostRepository implements HostRepository {
	
	private final Map<String, HostDto> hosts = new HashMap<>();
	

	@Override
	public String saveHost(HostDto host) {
		hosts.put(host.getIp(), host);
		return host.getIp();
	}

	@Override
	public Optional<HostDto> findHostByIp(String ip) {
		// TODO Auto-generated method stub
		return null;
	}

}
