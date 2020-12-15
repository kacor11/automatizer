package pl.kocjan.automatizer.domain.playbook;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kocjan.automatizer.domain.host.dto.HostDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	private enum Result {
		SUCCED, FAILED
	}
	String name;
	String command;
	LocalDateTime executionDate;
	Task executedTask;
	Result executionResult;
}
