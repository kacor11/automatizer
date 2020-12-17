package pl.kocjan.automatizer.domain.playbook.port;

import com.jcraft.jsch.Session;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.HostDto;

public interface HostConnection {
	Either<Error, Session> establishConnection(HostDto host);

	Either<Error, Success> execute(String command, Session session);
	
	
}
