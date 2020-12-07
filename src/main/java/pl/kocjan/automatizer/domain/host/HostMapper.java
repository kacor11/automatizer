package pl.kocjan.automatizer.domain.host;

import pl.kocjan.automatizer.domain.host.dto.HostDto;

class HostMapper {
	
	HostDto hostToDto(Host host) {
		return HostDto.builder()
				.id(host.getId())
				.ip(host.getIp())
				.port(host.getPort())
				.isAuthorized(host.isAuthorized())
				.groups(host.getGroups())
				.build();
	}
	
	Host dtoToHost(HostDto dto) {
		return Host.builder()
				.id(dto.getId())
				.ip(dto.getIp())
				.port(dto.getPort())
				.isAuthorized(dto.isAuthorized())
				.groups(dto.getGroups())
				.build();
		
	}
}
