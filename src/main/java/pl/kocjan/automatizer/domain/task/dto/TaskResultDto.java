package pl.kocjan.automatizer.domain.task.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaskResultDto {
	
	int exitValue;
}
