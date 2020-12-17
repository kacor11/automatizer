package pl.kocjan.automatizer.domain.host;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;
import pl.kocjan.automatizer.domain.playbook.Task;

@Builder
@Getter
public class Host {
	private Long id;
	private String ip;
	private String username;
	private int port;
	private boolean isAuthorized;
	private List<Task> executedTasks;
	private Set<String> groups;
	
	public static Host buildHost(CreateHostDto dto) {
				return Host.builder()
						.ip(dto.getIp())
						.port(dto.getPort())
						.groups(Set.of("Default"))
						.isAuthorized(false)
						.executedTasks(new ArrayList<>())
						.build();								
	}
	
		
	public void authorize() {
		this.isAuthorized = true;
	}
	
	public void submitTask(Task task) { 
		executedTasks.add(task);
	}
}
