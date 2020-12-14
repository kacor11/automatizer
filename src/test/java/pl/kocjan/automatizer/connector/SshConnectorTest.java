package pl.kocjan.automatizer.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.adapter.connector.ConnectionError;
import pl.kocjan.automatizer.adapter.connector.SshHostConnection;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.task.dto.TaskDto;
import pl.kocjan.automatizer.domain.task.dto.TaskResultDto;

public class SshConnectorTest {
	private final TaskDto validTaskDto = TaskDto.builder()
			.command("pwd")
			.build();
	private final TaskDto validTaskDto2 = TaskDto.builder()
			.command("ls")
			.build();
	
	private final TaskDto invalidIpTaskDto = TaskDto.builder()
			.command("ls")
			.build();
	
	private final HostDto validHostDto = 
			HostDto.builder()
			.ip("localhost")
			.port(22)
			.username("kacor11")
			.build();
			
			
		
		
	
	@Test
	public void ShouldReturnTaskResultAfterCommandRun() {
		//given
		List<TaskDto> tasks = new ArrayList<>();
		tasks.add(validTaskDto);
		tasks.add(validTaskDto2);
		SshHostConnection connection = new SshHostConnection();
		
		//when
		Either<Error, List<Either<Error, TaskResultDto>>> result = connection.runOnRemoteHost(tasks, validHostDto);
		
		//then
		assertTrue(result.isRight());
		
	}
	

	public void ShouldFailWhenConnectingWithWrongIp() {
		//given
		SshHostConnection connection = new SshHostConnection();
		
		//when
		//Either<Error, TaskResultDto> result = connection.runOnRemoteHost(invalidIpTaskDto);
		
		//then
		//assertEquals(ConnectionError.HOST_CONNECTION_ERROR, result.getLeft());
		
	}
}
