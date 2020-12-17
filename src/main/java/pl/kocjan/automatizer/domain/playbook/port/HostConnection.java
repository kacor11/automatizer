package pl.kocjan.automatizer.domain.playbook.port;

import java.util.List;

import com.jcraft.jsch.Session;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.Host;
import pl.kocjan.automatizer.domain.playbook.Task;

public interface HostConnection {
	Either<Error, Session> establishConnection(Host host);

	Either<Error, Success> execute(String command, Session session);
	
	
}
