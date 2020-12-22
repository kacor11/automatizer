package pl.kocjan.automatizer.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.user.UserFacade;
import pl.kocjan.automatizer.domain.user.dto.CreateUserDto;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final ResponseResolver responseResolver;
	private final UserFacade userFacade;
	
	@PostMapping("/create")
	ResponseEntity createHost(@RequestBody CreateUserDto createUserDto) {
		return responseResolver.resolve(userFacade.create(createUserDto));
	}
	
	@PostMapping("/activate")
	ResponseEntity activateHost(@RequestParam String username) {
		return responseResolver.resolve(userFacade.activateUser(username));
	}
	
	@GetMapping("/")
	ResponseEntity getWithUsername(@RequestParam String username) {
		return responseResolver.resolve(userFacade.readUser(username));
	}
	
	
}
