package pl.kocjan.automatizer.domain.playbook;

import io.vavr.control.Either;
import pl.kocjan.automatizer.adapter.connector.SshHostConnection;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.host.port.HostRepository;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;
import pl.kocjan.automatizer.domain.playbook.port.HostConnection;


public class PlaybookFacade {
	
	private final PlaybookCreator playbookCreator;
	private final PlaybookExecutor playbookExecutor;
	private final HostConnection hostConnection;
	private final HostRepository hostRepository;
	private final HostFacade hostFacade;
	
	public PlaybookFacade(HostRepository hostRepository, HostFacade hostFacade) {
		this.hostRepository = hostRepository;
		this.hostFacade = hostFacade;
		this.hostConnection = new SshHostConnection();
		this.playbookExecutor = new PlaybookExecutor(hostConnection, hostRepository);	
		this.playbookCreator = new PlaybookCreator(hostFacade);
	}
	
	public Either<Error, Success> runPlaybook(PlaybookDto playbookDto) {
		return playbookExecutor.execute(playbookDto);
	}
	
}
