package pl.kocjan.automatizer.domain.playbook.dto;

import java.util.List;

import io.vavr.control.Either;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.host.Host;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.playbook.Task;

@Getter
@RequiredArgsConstructor
public class PlaybookResultDto {
	private final Host host;
	private final List<Task> tasks;
	private List<Either<Error, TaskResultDto>> results;
	
	public void submitResult(Either<Error, TaskResultDto> taskResult) {
		results.add(taskResult);
	}
}
