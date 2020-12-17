package pl.kocjan.automatizer.domain.playbook.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.kocjan.automatizer.domain.playbook.Task;

@Getter
@AllArgsConstructor
@Builder
public class PlaybookDto {
	private final String hostGroup;
	private final List<Task> tasks;
}
