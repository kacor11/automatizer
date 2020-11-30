package pl.kocjan.automatizer.domain.host;

import java.net.InetSocketAddress;
import java.net.Socket;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.LocalCommandRunner;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.HostError;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

@RequiredArgsConstructor
class HostCreator {
	
	private final HostRepository hostRepository;
	private final LocalCommandRunner commandRunner;
	
	
			
}

