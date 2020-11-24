package pl.kocjan.automatizer.domain.common;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.NoArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.dto.HostError;

@NoArgsConstructor
public class LocalCommandRunner {
	
	public static void main(String[] args) {
		Either<HostError, Integer> result = execute("pwd", "7675277");
		System.out.println(result);
	}
	
	
	static Either<HostError, Success> execute(String command, String password) {
		
		String[] commands = command.split(" ");
		ProcessBuilder processBuilder = new ProcessBuilder(commands);		
				
		return Try.of(() -> processBuilder.start())
			.mapTry(process -> process.waitFor())
			.map(i -> {
				if(i == 0) {
					return Either.left(HostError.HOST_ALREADY_EXISTS);
				}
				return  new Success();
			})
			.toEither(HostError.HOST_ALREADY_EXISTS);		
	}			

}

