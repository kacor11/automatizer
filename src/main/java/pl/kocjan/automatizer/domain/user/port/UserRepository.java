package pl.kocjan.automatizer.domain.user.port;

import java.util.Optional;

import pl.kocjan.automatizer.domain.user.dto.UserDto;

public interface UserRepository {

	Optional<UserDto> findByEmail(String email);

	Optional<UserDto> findByUsername(String username);

	String saveUser(UserDto userToDto);

}
