package pl.kocjan.automatizer.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.playbook.PlaybookFacade;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;

@RestController
@RequestMapping("/playbook")
@RequiredArgsConstructor
public class PlaybookController {
	
	private final PlaybookFacade playbookFacade;
	private final ResponseResolver responseResolver;
	
	@PostMapping("/")
	ResponseEntity runPlaybook(PlaybookDto playbookDto) {
		return responseResolver.resolve(playbookFacade.runPlaybook(playbookDto));
	}
}
