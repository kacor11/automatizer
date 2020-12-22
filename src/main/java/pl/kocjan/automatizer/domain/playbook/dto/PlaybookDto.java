package pl.kocjan.automatizer.domain.playbook.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kocjan.automatizer.domain.playbook.Task;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaybookDto {
	private String hostGroup;
	private List<Task> tasks;
}
