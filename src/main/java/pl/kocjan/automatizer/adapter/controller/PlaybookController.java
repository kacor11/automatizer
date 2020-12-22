package pl.kocjan.automatizer.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.parser.YamlParser;
import pl.kocjan.automatizer.domain.parser.dto.ParsingError;
import pl.kocjan.automatizer.domain.playbook.PlaybookFacade;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;

@RestController
@RequestMapping("/playbook")
@RequiredArgsConstructor
public class PlaybookController {
	
	private final PlaybookFacade playbookFacade;
	private final ResponseResolver responseResolver;
	private final YamlParser yamlParser;
	
	@PostMapping("/")
	ResponseEntity runPlaybook(@RequestBody MultipartFile playbook) {
		Either<Error, byte[]> data = Try.of(() -> playbook.getBytes()).toEither(ParsingError.PARSING_YAML_FILE_FAILED);
		Either<Error, PlaybookDto> playbookDto = yamlParser.yamlToPlaybook(data.get());
		return playbookDto.isRight() ? 
				responseResolver.resolve(playbookFacade.runPlaybook(playbookDto.get())) :
					responseResolver.resolve(playbookDto);
	}
}
