package pl.kocjan.automatizer.domain.playbook;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	public enum Result {
		SUCCEED, FAILED
	}
	long id;
	String name;
	String command;
	LocalDateTime executionDate;
	Result executionResult;
	
	public void executionResult(Result result) {
		executionResult = result;
	}
	
	public void executedOn(LocalDateTime date) {
		executionDate = date;
	}
}
