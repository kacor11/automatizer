package pl.kocjan.automatizer.domain.user.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import pl.kocjan.automatizer.domain.common.Role;


@Getter
@Builder
public class UserDto {

	
	private Long id;
	private String username;
	private String password;
	private String email;
	private Role role;
	private LocalDateTime created;
}
