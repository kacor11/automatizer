package pl.kocjan.automatizer.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.host.dto.AuthorizeHostDto;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;

@RestController
@RequestMapping("/host")
@RequiredArgsConstructor
public class HostController {

	private final ResponseResolver responseResolver;
	private final HostFacade hostFacade;
	
	@PostMapping("/create")
	ResponseEntity createHost(@RequestBody CreateHostDto createHostDto) {
		return responseResolver.resolve(hostFacade.createHost(createHostDto));
	}
	
	@PostMapping("/authorize")
	ResponseEntity authorizeHost(@RequestBody AuthorizeHostDto authorizeHostDto) {
		return responseResolver.resolve(hostFacade.authorizeHost(authorizeHostDto));
	}
	
	@GetMapping("/") 
	ResponseEntity getWithIp(@RequestParam String ip) {	
		return responseResolver.resolve(hostFacade.readHost(ip));
	}
	
	@GetMapping("/group")
	ResponseEntity getWithGroup(@RequestParam String group) {
		return responseResolver.resolve(hostFacade.getHostsWithGroup(group));
	}
}
