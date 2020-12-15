package pl.kocjan.automatizer.domain.playbook.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kocjan.automatizer.domain.playbook.Task;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaybookDto {
	private String hostGroup;
	private List<Task> tasks;
}
