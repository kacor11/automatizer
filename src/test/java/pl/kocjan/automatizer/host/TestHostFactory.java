package pl.kocjan.automatizer.host;

import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;

class TestHostFactory {
	static final String IP = "111.111.111";
	static final String IP2 = "222.222.222";
	static final String PASSWORD = "11111";
	static final int PORT = 11;
	
	static CreateHostDto validHostCreationDto() {
		return CreateHostDto.builder()
				.ip(IP)
				.password(PASSWORD)
				.port(PORT)
				.build();
	}
	
	static CreateHostDto validHostCreationDto2() {
		return CreateHostDto.builder()
				.ip(IP2)
				.password(PASSWORD)
				.port(PORT)
				.build();
	}
}
