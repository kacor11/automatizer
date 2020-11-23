package pl.kocjan.automatizer.domain.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.vavr.control.Either;
import io.vavr.control.Try;
import pl.kocjan.automatizer.domain.common.vavr.Success;

public class CommandRunner {
	Either<Error, Success> execute(String command) {
		String[] commands = command.split(" ");
		ProcessBuilder pb = new ProcessBuilder(commands);
		
		Try<Process> process = Try.of(() -> pb.start());

		Try.withResources(() -> new BufferedReader(new InputStreamReader(process.getInputStream())));
	}
			

}
