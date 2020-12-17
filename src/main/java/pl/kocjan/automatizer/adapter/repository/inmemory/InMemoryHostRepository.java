package pl.kocjan.automatizer.adapter.repository.inmemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;


public class InMemoryHostRepository implements HostRepository {
	
	private final Map<String, HostDto> hosts = new HashMap<>();
	
	public InMemoryHostRepository() {
		hosts.put("host1", HostDto.builder()
				.ip("localhost")
				.port(22)
				.groups(Set.of("DEFAULT"))
				.executedTasks(new ArrayList<>())
				.build());
		
		hosts.put("host2", HostDto.builder()
				.ip("ip2")
				.port(22)
				.groups(Set.of("NOT_DEFAULT"))
				.build());
	}
	

	@Override
	public String saveHost(HostDto host) {
		hosts.put(host.getIp(), host);
		return host.getIp();
	}

	@Override
	public Optional<HostDto> findHostByIp(String ip) {
		return Optional.ofNullable(hosts.get(ip));
	}

	@Override
	public List<HostDto> findHostsByGroup(String group) {
		return hosts.entrySet()
				.stream()
				.filter(e -> e.getValue().getGroups().contains(group))
				.map(Map.Entry::getValue)
				.collect(Collectors.toList());	
	}

}
