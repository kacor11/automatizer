package pl.kocjan.automatizer.domain.playbook;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import pl.kocjan.automatizer.domain.host.Host;


@Builder
@Getter
public class Playbook {
	private Host host;
	private List<Task> tasks;
}
