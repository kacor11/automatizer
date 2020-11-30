package pl.kocjan.automatizer.common;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.LocalCommandRunner;
import pl.kocjan.automatizer.domain.common.dto.CommandRunnerError;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.common.vavr.Error;



public class LocalCommandRunnerTest {
	

	private static final String INVALID_COMMAND = "xyz";
	private static final String VALID_COMMAND = "pwd";


	@Test
	public void shouldFailWithInvalidShellCommand() {
		//given
		LocalCommandRunner commandRunner = new LocalCommandRunner();
		
		//when
		Either<Error,Success> result =  commandRunner.processCommand(INVALID_COMMAND);
		
		
		//then
		assertEquals(result.getLeft(), CommandRunnerError.COMMAND_EXECUTION_ERROR);
		
	}
	
	@Test
	public void shouldSucceedWithValidShellCommand() {
		//given
		LocalCommandRunner commandRunner = new LocalCommandRunner();
		
		//when
		Either<Error,Success> result =  commandRunner.processCommand(VALID_COMMAND);
		
		
		//then
		assertEquals(result.getOrElseThrow(t -> new RuntimeException()), new Success());
	}
}
