package pl.kocjan.automatizer.domain.common;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.NoArgsConstructor;
import pl.kocjan.automatizer.domain.common.dto.CommandRunnerError;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;

@NoArgsConstructor
public class LocalCommandRunner {
	
	private static final int SUCCESS_EXIT_VALUE = 0;
	private static final int WRONG_PASSWORD_EXIT_VALUE = 5;
	
	
	public String createSshCopyCommand(String password, String username, String ip) {
		System.out.println(password);
		return "sshpass -p " + password + " ssh-copy-id -o StrictHostKeyChecking=no " + username + "@" + ip;
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
		System.out.println(exitValue);
		switch(exitValue) {
		case SUCCESS_EXIT_VALUE:
			return Either.right(new Success());
			
		case WRONG_PASSWORD_EXIT_VALUE:
			return Either.left(CommandRunnerError.INCORRECT_SSH_PASSWORD);		
		
		default:
			return Either.left(CommandRunnerError.COMMAND_EXECUTION_ERROR);	
		}
	}
	
	

}

