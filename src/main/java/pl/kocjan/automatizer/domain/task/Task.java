package pl.kocjan.automatizer.domain.task;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class Task {
	private enum Status {
		FAILED, EXECUTED;
	};
 	private final String name;
	private final String command;
}
