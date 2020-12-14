package pl.kocjan.automatizer.domain.playbook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookError;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;
import pl.kocjan.automatizer.domain.task.dto.TaskDto;
import pl.kocjan.automatizer.domain.task.dto.TaskResultDto;

@RequiredArgsConstructor
class PlaybookExecutor {
	private final HostRepository hostRepository;
	private final HostConnection hostConnection;
		
	Either<Error, Success> execute(PlaybookDto playbookDto) {
		// TODO Auto-generated method stub
	}
	
	Either<Error, List<HostDto>> getRequiredHosts(PlaybookDto playbookDto) {
		return Option
				.ofOptional(hostRepository.findHostsByGroup(playbookDto.getHostGroup()))
				.toEither(PlaybookError.HOSTS_GROUP_ERROR);
	}
	List<HostDto> executeTasks(List<HostDto> hostList, PlaybookDto playbookDto) {
		List<CompletableFuture<Either<Error, TaskResultDto>>> completableFutures = new ArrayList<>();
	}
	
	CompletableFuture<Either<Error, TaskResultDto>> executeTaskAsync(HostDto hostDto, TaskDto taskDto) {
		return CompletableFuture.supplyAsync(() -> 
			hostConnection.runOnRemoteHost(taskDto, hostDto));
	}
}