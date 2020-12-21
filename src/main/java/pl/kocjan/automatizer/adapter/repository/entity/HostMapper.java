package pl.kocjan.automatizer.adapter.repository.entity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.playbook.Task;

@Component
public class HostMapper {
	
	@Autowired
	private TaskMapper taskMapper;
	public HostEntity dtoToHostEntity(HostDto dto) {
		System.out.println(dto.getGroups());
		return HostEntity.builder()
				.id(dto.getId())
				.ip(dto.getIp())
				.hostPort(dto.getPort())
				.isAuthorized(dto.isAuthorized())
				.executedTasks(mapTaskToEntities(dto.getExecutedTasks()))
				.hostGroups(dto.getGroups())
				.build();
	}
	
	public HostDto hostEntityToDto(HostEntity entity) {
		return HostDto.builder()
				.id(entity.getId())
				.ip(entity.getIp())
				.port(entity.getHostPort())
				.isAuthorized(entity.getIsAuthorized())
				.executedTasks(mapEntitiesToTasks(entity.getExecutedTasks()))
				.groups(entity.getHostGroups())
				.build();
	}
	
	private List<TaskEntity> mapTaskToEntities(List<Task> tasks) {
		return tasks.stream()
			.map(task -> taskMapper.taskToEntity(task))
			.collect(Collectors.toList());
	}
	
	private List<Task> mapEntitiesToTasks(List<TaskEntity> tasks) {
		return tasks.stream()
			.map(task -> taskMapper.EntityToTask(task))
			.collect(Collectors.toList());
	}
}
