package pl.kocjan.automatizer.domain.common;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.NoArgsConstructor;
import pl.kocjan.automatizer.domain.common.dto.CommandRunnerError;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.common.vavr.Error;

@NoArgsConstructor
public class LocalCommandRunner {
	
	private static final Integer SUCCESS_EXIT_VALUE = 0;
	
	
	public String createSshCopyCommand(String password, String username, String ip) {
		return "sshpass -p " + password + " ssh-copy-id " + username + "@" + ip;
	}
	
	
	public Either<Error, Success> processCommand (String command) {
		return this.execute(command)
				.flatMap(this::interpreteExitValue);
	}
	
	private Either<Error, Integer> execute(String command) {
		
		String[] commands = command.split(" ");
		ProcessBuilder processBuilder = new ProcessBuilder(commands);		
				
		return Try.of(() -> processBuilder.start())
			.mapTry(process -> process.waitFor())
			.toEither(CommandRunnerError.COMMAND_EXECUTION_ERROR);
	}
	
	private Either<Error, Success> interpreteExitValue(Integer exitValue) {
		//TODO add specific errors depending on the exit value
		System.out.println(exitValue);
		return exitValue == SUCCESS_EXIT_VALUE ? Either.right(new Success()) : 
			Either.left(CommandRunnerError.COMMAND_EXECUTION_ERROR);
	}
	
	
	

}

