package pl.kocjan.automatizer.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class CreateUserDto {
	
	private final String username;
	private final String password;
	private final String email;
	
}
