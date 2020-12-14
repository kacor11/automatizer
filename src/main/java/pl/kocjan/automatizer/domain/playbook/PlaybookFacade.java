package pl.kocjan.automatizer.domain.playbook;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.host.port.HostRepository;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;


public class PlaybookFacade {
	
	private final PlaybookExecutor playbookExecutor;
	
	public PlaybookFacade(HostRepository hostRepository) {
		this.playbookExecutor = new PlaybookExecutor(hostRepository);
	}
	
	public Either<Error, Success> runPlaybook(PlaybookDto playbookDto) {
		return playbookExecutor.execute(playbookDto);
	}
}
