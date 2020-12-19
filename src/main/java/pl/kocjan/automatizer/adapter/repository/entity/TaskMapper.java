package pl.kocjan.automatizer.adapter.repository.entity;

import org.springframework.stereotype.Component;

import pl.kocjan.automatizer.domain.playbook.Task;
import pl.kocjan.automatizer.domain.playbook.Task.Result;

@Component
public class TaskMapper {
	public TaskEntity taskToEntity(Task task) {
		return TaskEntity.builder()
			.id(task.getId())
			.taskName(task.getName())
			.taskCommand(task.getCommand())
			.executionDate(task.getExecutionDate())
			.executionResult(parseExecutionResult(task.getExecutionResult()))
			.build();
	}
	
	public Task EntityToTask(TaskEntity entity) {
		return Task.builder()
			.id(entity.getId())
			.name(entity.getTaskName())
			.command(entity.getTaskCommand())
			.executionDate(entity.getExecutionDate())
			.executionResult(parseToResult(entity.getExecutionResult()))
			.build();
	}
	
	private String parseExecutionResult(Result result) {
		return result.equals(Result.SUCCEED) ? "SUCCEED" : "FAILED";
	}
	
	private Result parseToResult(String result) {
		return result.equals("SUCCEED") ? Result.SUCCEED : Result.FAILED;
	}
	
}
