package pl.kocjan.automatizer.playbook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.adapter.connector.SshHostConnection;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.playbook.PlaybookExecutor;
import pl.kocjan.automatizer.domain.playbook.Task;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;
import pl.kocjan.automatizer.domain.playbook.dto.TaskResultDto;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;

public class PlaybookExecutorTest {
	
	private final Task validTaskDto = Task.builder()
			.command("pwd")
			.build();
	private final Task validTaskDto2 = Task.builder()
			.command("ls")
			.build();
	
	private final Task invalidIpTaskDto = Task.builder()
			.command("ls")
			.build();
	
	private final HostDto validHostDto = 
			HostDto.builder()
			.ip("localhost")
			.port(22)
			.username("kacor11")
			.build();

	
	@Test 
	public void asf() {
		HostConnection con = new SshHostConnection();
		List<HostDto> hosts = new ArrayList<>();
		hosts.add(validHostDto);
		hosts.add(validHostDto);
		hosts.add(validHostDto);
		hosts.add(validHostDto);
		List<Task> tasks = new ArrayList<>();
		tasks.add(validTaskDto);
		tasks.add(validTaskDto2);
		tasks.add(validTaskDto);
		tasks.add(validTaskDto2);
		tasks.add(validTaskDto);
		tasks.add(validTaskDto2);
		PlaybookExecutor pe = new PlaybookExecutor(con);
		PlaybookDto pb = new PlaybookDto("dupa", tasks);
		Either<Error, List<Either<Error, TaskResultDto>>> result;
			result = pe.executeTasks(hosts, pb).join().get(0);
			System.out.println(result);
			System.out.println(result);
			System.out.println(result);
		
		
	}
}
