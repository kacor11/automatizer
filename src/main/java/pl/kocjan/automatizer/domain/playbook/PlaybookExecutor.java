package pl.kocjan.automatizer.domain.playbook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookError;
import pl.kocjan.automatizer.domain.playbook.dto.TaskResultDto;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;

@RequiredArgsConstructor
public class PlaybookExecutor {
	private final HostConnection hostConnection;
	private final HostRepository hostRepository;
	
	Either<Error, Success> execute(PlaybookDto playbookDto) {
		return 

	}		
		
		
	private CompletableFuture<List<Either<Error, List<Either<Error, TaskResultDto>>>>> executePlaybooks(List<Playbook> playbooks) {
		List<CompletableFuture<Either<Error, List<Either<Error, TaskResultDto>>>>> completableFutures =  
				playbooks.stream()
				.map(playbook -> executePlaybookOnSingleHost(playbook))
				.collect(Collectors.toList());
		
		return CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture<?>[0]))
			.thenApply(v -> completableFutures.parallelStream()
					.map(CompletableFuture::join)
					.collect(Collectors.toList()));
	}
	
	private CompletableFuture<Either<Error, List<Either<Error, TaskResultDto>>>> executePlaybookOnSingleHost(Playbook playbook) {
		return CompletableFuture.supplyAsync(() -> 
			hostConnection.runOnRemoteHost(playbook.getTasks(), playbook.getHost()), Executors.newFixedThreadPool(10));
	}


	
}