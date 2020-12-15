package pl.kocjan.automatizer.domain.playbook.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.playbook.Task;

@Builder
@Getter
public class TaskResultDto {
	HostDto executedOn;
	LocalDateTime executionDate;
	Task executedTask;
	int exitValue;
}
