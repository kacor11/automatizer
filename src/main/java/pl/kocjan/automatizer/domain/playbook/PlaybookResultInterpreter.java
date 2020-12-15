package pl.kocjan.automatizer.domain.playbook;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.playbook.dto.TaskResultDto;

public class PlaybookResultInterpreter {
	Either<Error, Success> process(CompletableFuture<List<Either<Error, List<Either<Error, TaskResultDto>>>>> resultList) {
		
	}
}
