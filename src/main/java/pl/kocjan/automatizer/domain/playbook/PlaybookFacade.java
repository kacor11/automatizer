package pl.kocjan.automatizer.domain.playbook;

import java.util.List;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.host.Host;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.host.port.HostRepository;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;


public class PlaybookFacade {
	
	private final PlaybookCreator playbookCreator;
	private final PlaybookExecutor playbookExecutor;
	private final HostConnection hostConnection;
	private final HostFacade hostFacade;
	
	public PlaybookFacade(HostRepository hostRepository, HostFacade hostFacade, HostConnection hostConnection) {
		this.hostFacade = hostFacade;
		this.hostConnection = hostConnection;
		this.playbookCreator = new PlaybookCreator(hostFacade);
		this.playbookExecutor = new PlaybookExecutor(hostConnection, playbookCreator, hostFacade);	
	}
	
	public Either<Error, List<Either<Error, Host>>> runPlaybook(PlaybookDto playbookDto) {
		return playbookExecutor.execute(playbookDto);
	}
	
}
