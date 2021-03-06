package pl.kocjan.automatizer.domain.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.vavr.control.Either;
import io.vavr.control.Try;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.parser.dto.ParsingError;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;

public class YamlParser {
	
	
	public Either<Error, PlaybookDto> yamlToPlaybook(byte[] byteArr) {
		ObjectMapper mapper;
		mapper = new ObjectMapper(new YAMLFactory());
		return Try.of(() -> mapper.readValue(byteArr, PlaybookDto.class))
				.toEither(ParsingError.PARSING_YAML_FILE_FAILED);
	}
	
}
