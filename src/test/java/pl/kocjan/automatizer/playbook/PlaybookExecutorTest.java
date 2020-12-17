package pl.kocjan.automatizer.playbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.adapter.connector.SshHostConnection;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.Host;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;
import pl.kocjan.automatizer.domain.host.dto.HostDto;
import pl.kocjan.automatizer.domain.playbook.Playbook;
import pl.kocjan.automatizer.domain.playbook.PlaybookExecutor;
import pl.kocjan.automatizer.domain.playbook.Task;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;

public class PlaybookExecutorTest {
	
	private final Task validTaskDto = Task.builder()
			.command("pwd")
			.build();
	private final Task validTaskDto2 = Task.builder()
			.command("ls")
			.build();
	
	private final Task invalidIpTaskDto = Task.builder()
			.command("lsss")
			.build();
	
	private final HostDto validHostDto = 
			HostDto.builder()
			.ip("localhost")
			.port(22)
			.username("kacor11")
			.build();

	
	@Test 
	public void asf() {
		List<HostDto> hosts = new ArrayList<>();
		hosts.add(validHostDto);
		hosts.add(validHostDto);
		hosts.add(validHostDto);
		hosts.add(validHostDto);
		List<Task> tasks = new ArrayList<>();
		tasks.add(validTaskDto);
		tasks.add(invalidIpTaskDto);
		tasks.add(invalidIpTaskDto);
		tasks.add(validTaskDto2);
		tasks.add(validTaskDto);
		tasks.add(validTaskDto2);
		CreateHostDto hostCreate = CreateHostDto.builder()
					.ip("localhost")
					.port(22)
					.build();

		
		HostConnection conn = new SshHostConnection();
		PlaybookExecutor pe = new PlaybookExecutor(conn);
		Host host = Host.buildHost(hostCreate);
		Playbook pb = new Playbook(host, tasks);

			Either<Error, Success> result = pe.executePlaybookOnSingleHost(pb);

		System.out.println(result);
		pb.getHost().getExecutedTasks().forEach(task -> System.out.println(task.getExecutionDate()));
		assertEquals(result.isRight(), true);
		
	}
}
