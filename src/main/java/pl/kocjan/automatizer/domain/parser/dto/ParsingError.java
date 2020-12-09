package pl.kocjan.automatizer.domain.parser.dto;

import lombok.AllArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;

@AllArgsConstructor
public enum ParsingError implements Error {
	PARSING_YAML_FILE_FAILED("There was an error while parsing YAML file you provided");
	
	private final String error;

	@Override
	public String getError() {
		return this.error;
	}

}
