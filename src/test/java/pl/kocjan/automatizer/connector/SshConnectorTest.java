package pl.kocjan.automatizer.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.adapter.connector.ConnectionError;
import pl.kocjan.automatizer.adapter.connector.SshHostConnection;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.task.dto.TaskDto;
import pl.kocjan.automatizer.domain.task.dto.TaskResultDto;

public class SshConnectorTest {
	private final TaskDto validTaskDto = TaskDto.builder()
			.uuid(UUID.randomUUID().toString())
			.hostName("kacor11")
			.hostIp("localhost")
			.hostPort(22)
			.name("task name")
			.command("pwd")
			.build();
	
	private final TaskDto invalidIpTaskDto = TaskDto.builder()
			.uuid(UUID.randomUUID().toString())
			.hostName("kacor11")
			.hostIp("123")
			.hostPort(22)
			.name("task name")
			.command("pwd")
			.build();
	
	
	
	@Test
	public void ShouldReturnTaskResultAfterCommandRun() {
		//given
		SshHostConnection connection = new SshHostConnection();
		
		//when
		Either<Error, TaskResultDto> result = connection.runOnRemoteHost(validTaskDto);
		
		//then
		System.out.println(result.isRight());
		assertTrue(result.isRight());
		
	}
	
	@Test
	public void ShouldFailWhenConnectingWithWrongIp() {
		//given
		SshHostConnection connection = new SshHostConnection();
		
		//when
		Either<Error, TaskResultDto> result = connection.runOnRemoteHost(invalidIpTaskDto);
		
		//then
		assertEquals(ConnectionError.HOST_CONNECTION_ERROR, result.getLeft());
		
	}
}
