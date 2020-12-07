package pl.kocjan.automatizer.host;

import pl.kocjan.automatizer.domain.host.dto.AuthorizeHostDto;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;

class TestHostFactory {
	static final String IP = "111.111.111";
	static final String IP2 = "222.222.222";
	static final String LOCALHOST_PASSWORD = "password";
	static final String INCORRECT_LOCALHOST_PASSWORD = "asdf";
	static final String LOCALHOST_USERNAME = "user";
	static final int PORT = 22;
	
	static CreateHostDto validHostCreationDto() {
		return CreateHostDto.builder()
				.ip(IP)
				.port(PORT)
				.build();
	}
	
	static CreateHostDto validHostCreationDto2() {
		return CreateHostDto.builder()
				.ip(IP2)
				.port(PORT)
				.build();
	}
	
	static CreateHostDto validHostCreationDtoToAuthorize() {
		return CreateHostDto.builder()
				.ip("localhost")
				.port(22)
				.build();
	}
	
	static AuthorizeHostDto validAuthorizationHostDto() {
		return AuthorizeHostDto.builder()
				.ip("localhost")
				.sshPassword(LOCALHOST_PASSWORD)
				.username(LOCALHOST_USERNAME)
				.build();
	}
	
	static AuthorizeHostDto invalidAuthorizationHostDto() {
		return AuthorizeHostDto.builder()
				.ip("localhost")
				.sshPassword(INCORRECT_LOCALHOST_PASSWORD)
				.username(LOCALHOST_USERNAME)
				.build();
	}
}
