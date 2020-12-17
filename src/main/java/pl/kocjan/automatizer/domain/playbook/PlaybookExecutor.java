package pl.kocjan.automatizer.domain.playbook;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.jcraft.jsch.Session;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.Host;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;

@RequiredArgsConstructor
class PlaybookExecutor {
	private final HostConnection hostConnection;
	private final PlaybookCreator playbookCreator;
	private final HostFacade hostFacade;
	
	Either<Error, List<Either<Error, Host>>> execute(PlaybookDto playbookDto) {
		return playbookCreator.createForGroup(playbookDto)
			.map(playbooks -> executePlaybookOnMultipleHosts(playbooks));
	}
	
	List<Either<Error, Host>> executePlaybookOnMultipleHosts(List<Playbook> playbooks) {
		
		List<CompletableFuture<Either<Error, Host>>> futureList = 
				playbooks.stream()
				.map(playbook -> {
					return CompletableFuture.supplyAsync(() -> executePlaybookOnSingleHost(playbook));
				})
				.collect(Collectors.toList());
		
		CompletableFuture<Void> allFuturesResult =
				CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
		
		return allFuturesResult.thenApply(v -> 
				futureList.stream()
				.map(future -> future.join())
				.collect(Collectors.<Either<Error, Host>>toList())).join();				
	}
	
	 Either<Error, Host> executePlaybookOnSingleHost(Playbook playbook) {
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
		}
		Either<Error, Success> updateHostData = hostFacade.updateHost(host);
		return updateHostData.isLeft() ? Either.left(updateHostData.getLeft()) : Either.right(host);
	}

	
}