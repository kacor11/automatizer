package pl.kocjan.automatizer.domain.host;

import java.net.InetSocketAddress;
import java.net.Socket;

import io.vavr.control.Either;
import io.vavr.control.Try;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.ConnectionDetailsDto;
import pl.kocjan.automatizer.domain.host.dto.HostError;

class HostAviabilityChecker {

	 Either<Error, Success> checkHostAvailability(ConnectionDetailsDto connection) {
		int timeout = 500;
		return Try.withResources(() -> new Socket())
			.of(socket -> {
				socket.connect(new InetSocketAddress(connection.getIp(), connection.getPort()), timeout);
				return new Success("Host is currently available");
			})
			.toEither(HostError.ESTABLISHING_CONNECTION_FAILED);					
    }
}
