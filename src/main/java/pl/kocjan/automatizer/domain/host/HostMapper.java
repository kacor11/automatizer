package pl.kocjan.automatizer.domain.host;

import pl.kocjan.automatizer.domain.host.dto.HostDto;


public class HostMapper {
	

	
	public HostDto hostToDto(Host host) {
		return HostDto.builder()
				.id(host.getId())
				.ip(host.getIp())
				.port(host.getPort())
				.executedTasks(host.getExecutedTasks())
				.isAuthorized(host.isAuthorized())
				.groups(host.getGroups())
				.build();
	}
	
	public Host dtoToHost(HostDto dto) {
		return Host.builder()
				.id(dto.getId())
				.ip(dto.getIp())
				.port(dto.getPort())
				.executedTasks(dto.getExecutedTasks())
				.isAuthorized(dto.isAuthorized())
				.groups(dto.getGroups())
				.build();
		
	}
}
