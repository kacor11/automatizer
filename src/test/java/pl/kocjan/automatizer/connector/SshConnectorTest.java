package pl.kocjan.automatizer.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.jcraft.jsch.Session;

import io.vavr.control.Either;
import pl.kocjan.automatizer.adapter.connector.ConnectionError;
import pl.kocjan.automatizer.adapter.connector.SshHostConnection;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.HostDto;

public class SshConnectorTest {
	private final String validCommand = "pwd";

		
	private final HostDto validHostDto = 
			HostDto.builder()
			.ip("localhost")
			.port(22)
			.username("kacor11")
			.build();
	
	private final HostDto invalidHostDto = 
			HostDto.builder()
			.ip("2345")
			.port(22)
			.username("kacor11")
			.build();
			
			
		
		
	
	@Test
	public void shouldEstablishConnectionWithRemoteHost() {
		
		//given
		SshHostConnection connection = new SshHostConnection();
		
		//when
		Either<Error, Session> result = connection.establishConnection(validHostDto);
		
		//then
		assertTrue(result.isRight());
		
	}
	
	@Test
	public void shouldFailWhenConnectingWithWrongIp() {
		//given
		SshHostConnection connection = new SshHostConnection();
		
		//when
		Either<Error, Session> result = connection.establishConnection(invalidHostDto);
		
		//then
		assertEquals(ConnectionError.HOST_CONNECTION_ERROR, result.getLeft());
		
	}
	
	@Test
	public void shouldExecuteCommandWithCorrectHostAndCommand() {
		//given
		SshHostConnection connection = new SshHostConnection();
		Either<Error, Session> session = connection.establishConnection(validHostDto);
		
		//when
		Either<Error, Success> result = connection.execute(validCommand, session.get());
		
		//then
		assertEquals(Either.right(new Success()), result);		
	}
}
