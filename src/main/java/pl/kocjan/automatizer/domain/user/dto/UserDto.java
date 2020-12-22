package pl.kocjan.automatizer.domain.user.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import pl.kocjan.automatizer.domain.common.Role;


@Getter
@Builder
public class UserDto {

	
	private final Long id;
	private final String username;
	private final String password;
	private final String email;
	private final Role role;
	private final LocalDateTime created;
}
