package pl.kocjan.automatizer.domain.playbook.port;

import java.util.List;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.host.Host;
import pl.kocjan.automatizer.domain.playbook.Task;
import pl.kocjan.automatizer.domain.playbook.dto.TaskResultDto;

public interface HostConnection {
	Either<Error, List<Either<Error, TaskResultDto>>> runOnRemoteHost(List<Task> taskList, Host host);
}
