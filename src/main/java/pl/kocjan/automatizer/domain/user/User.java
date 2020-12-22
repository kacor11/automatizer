package pl.kocjan.automatizer.domain.user;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import pl.kocjan.automatizer.domain.common.Role;
import pl.kocjan.automatizer.domain.user.dto.CreateUserDto;


@Builder
@Getter
class User {
	
	private final Long id;
	private final String username;
	private final String password;
	private final String email;
	private Role role;
	private final LocalDateTime created;
	
	static User createUser(CreateUserDto dto) {
		return User.builder()
				.username(dto.getUsername())
				.password(dto.getPassword())
				.email(dto.getEmail())
				.role(Role.NOT_VERIFIED)
				.created(LocalDateTime.now())
				.build();
	}
	
	void verifyUser() {
		this.role = Role.VERIFIED;
	}
}
