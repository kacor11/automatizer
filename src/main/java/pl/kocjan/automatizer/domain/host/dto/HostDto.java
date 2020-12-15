package pl.kocjan.automatizer.domain.host.dto;

import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import pl.kocjan.automatizer.domain.playbook.dto.TaskResultDto;

@Builder
@Getter
public class HostDto {
	private final Long id;
	private final String ip;
	private String username;
	private final int port;
	private final Set<String> groups;
	private final List<TaskResultDto> taskHistory;
	private boolean isAuthorized;	
}
