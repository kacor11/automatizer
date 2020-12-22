package pl.kocjan.automatizer.playbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.kocjan.automatizer.adapter.connector.SshHostConnection;
import pl.kocjan.automatizer.adapter.repository.inmemory.InMemoryHostRepository;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.host.port.HostRepository;
import pl.kocjan.automatizer.domain.playbook.PlaybookFacade;
import pl.kocjan.automatizer.domain.playbook.Task;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;

public class PlaybookExecutorTest {
	
	private final Task validTaskDto = Task.builder()
			.command("pwd")
			.build();
	private final Task validTaskDto2 = Task.builder()
			.command("ls")
			.build();
		
	HostRepository repository = new InMemoryHostRepository();
	HostFacade hostFacade = new HostFacade(repository);
	HostConnection sshConnection = new SshHostConnection();
	private final PlaybookFacade playbookFacade = new PlaybookFacade(repository, hostFacade, sshConnection);

	
	@Test 
	public void shouldExecutePlaybook() {
		
		//given
		PlaybookDto playbookDto = PlaybookDto.builder()
				.hostGroup("DEFAULT")
				.tasks(List.of(validTaskDto, validTaskDto2))
				.build();
		
		//when
		var result = playbookFacade.runPlaybook(playbookDto);
		
		//then
		assertTrue(result.isRight());
	}

}
