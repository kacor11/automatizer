package pl.kocjan.automatizer.domain.playbook.port;

import java.util.List;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.task.dto.TaskDto;
import pl.kocjan.automatizer.domain.task.dto.TaskResultDto;

public interface HostConnection {
	Either<Error, List<Either<Error, TaskResultDto>>> runOnRemoteHost(List<TaskDto> taskList, HostDto hostDto);
}
