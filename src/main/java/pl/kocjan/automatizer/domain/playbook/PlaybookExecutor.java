package pl.kocjan.automatizer.domain.playbook;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.jcraft.jsch.Session;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.Host;
import pl.kocjan.automatizer.domain.host.port.HostRepository;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;
import pl.kocjan.automatizer.domain.playbook.dto.TaskResultDto;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;

@RequiredArgsConstructor
public class PlaybookExecutor {
	private final HostConnection hostConnection;
	private final HostRepository hostRepository;
	
	Either<Error, Success> execute(PlaybookDto playbookDto) {
		return 

	}		
	
	private Either<Error, Success> executePlaybookOnSingleHost(Playbook playbook) {
		Host host = playbook.getHost();
		Either<Error, Session> session = hostConnection.establishConnection(host);
		if(session.isLeft()) {
			return Either.left(session.getLeft());
		}
		List<Task> tasks = playbook.getTasks();
		for(Task task : tasks) {
			Either<Error, Success> result = hostConnection.execute(task.getCommand(), session.get());
			task.executedOn(LocalDateTime.now());
			if(result.isLeft()) {
				task.executionResult(Task.Result.FAILED);
			} else {
				task.executionResult(Task.Result.SUCCEED);
		}
			host.submitTask(task);
			return Either.right(new Success());
	}

	

	
}