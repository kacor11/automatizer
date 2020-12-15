package pl.kocjan.automatizer.domain.playbook;

import java.util.List;
import java.util.stream.Collectors;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.host.Host;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookError;

@RequiredArgsConstructor
class PlaybookCreator {
	private final HostFacade hostFacade;
	
	Either<Error, List<Playbook>> createForGroup(PlaybookDto playbookDto) {
		List<Host> hosts = getRequiredHosts(playbookDto);
		return hosts.isEmpty() ? Either.left(PlaybookError.HOSTS_GROUP_ERROR) : 
			Either.right(hosts.stream()
			.map(host -> createPlaybook(host, playbookDto.getTasks()))
			.collect(Collectors.toList()));
	}	
		
	List<Host> getRequiredHosts(PlaybookDto playbookDto) {
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
