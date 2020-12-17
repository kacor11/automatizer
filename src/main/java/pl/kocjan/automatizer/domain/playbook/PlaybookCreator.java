package pl.kocjan.automatizer.domain.playbook;

import java.util.List;
import java.util.stream.Collectors;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.host.Host;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.host.HostMapper;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookError;

@RequiredArgsConstructor
class PlaybookCreator {
	private final HostFacade hostFacade;
	private final HostMapper hostMapper;
	
	Either<Error, List<Playbook>> createForGroup(PlaybookDto playbookDto) {
		List<HostDto> hosts = getRequiredHosts(playbookDto);
		return hosts.isEmpty() ? Either.left(PlaybookError.HOSTS_GROUP_ERROR) : 
			Either.right(hosts.stream()
			.map(host -> createPlaybook(hostMapper.dtoToHost(host), playbookDto.getTasks()))
			.collect(Collectors.toList()));
	}	
		
	List<HostDto> getRequiredHosts(PlaybookDto playbookDto) {
		return hostFacade.getHostsWithGroup(playbookDto.getHostGroup());
	}
	
	Host createPlaybooks(List<Host> list) {
		return list.get(0);
	}
	
	Playbook createPlaybook(Host host, List<Task> tasks) {
		return Playbook.builder()
				.host(host)
				.tasks(tasks)
				.build();
	}
}
