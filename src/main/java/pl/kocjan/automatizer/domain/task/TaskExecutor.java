package pl.kocjan.automatizer.domain.task;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.host.HostFacade;

@RequiredArgsConstructor
class TaskExecutor {
	
	private final HostFacade hostFacade;
	
}
