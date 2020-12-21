package pl.kocjan.automatizer.adapter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.dto.CommandRunnerError;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.host.dto.HostError;
import pl.kocjan.automatizer.domain.parser.dto.ParsingError;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookError;

@Component
public class ResponseResolver {
	private final Map<Error, HttpStatus> httpStatusErrors = new HashMap<>();
	
	ResponseResolver() {
		httpStatusErrors.put(CommandRunnerError.COMMAND_EXECUTION_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		httpStatusErrors.put(CommandRunnerError.INCORRECT_SSH_PASSWORD, HttpStatus.BAD_REQUEST);
		httpStatusErrors.put(HostError.HOST_ALREADY_EXISTS, HttpStatus.CONFLICT);
		httpStatusErrors.put(HostError.ESTABLISHING_CONNECTION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
		httpStatusErrors.put(HostError.DATABASE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		httpStatusErrors.put(HostError.HOST_DOESNT_EXIST, HttpStatus.NOT_FOUND);
		httpStatusErrors.put(ParsingError.PARSING_YAML_FILE_FAILED, HttpStatus.BAD_REQUEST);
		httpStatusErrors.put(PlaybookError.HOSTS_GROUP_ERROR, HttpStatus.BAD_REQUEST);		
	}
	 <T> ResponseEntity<?> resolve(Either<Error, T> either) {
		return either
				.map(this::succesResponse)
				.getOrElseGet(this::failureResponse);				
	}
	 
	 <T> ResponseEntity<?> resolve(Optional<T> optional) {
		 return optional
				 .map(value -> new ResponseEntity<>(optional, HttpStatus.OK))
				 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	 }
	 
	 <T> ResponseEntity<?> resolve(List<T> list) {
		 return succesResponse(list);
	 }
	
	private ResponseEntity<Object> succesResponse(Object obj) {
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
	private ResponseEntity<Object> failureResponse(Error error) {
		return new ResponseEntity<>(error, errorStatus(error));
	}
	
	private HttpStatus errorStatus(Error error) {
		return httpStatusErrors.get(error);
	}
	
}
