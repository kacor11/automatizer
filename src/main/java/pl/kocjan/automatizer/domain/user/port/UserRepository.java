package pl.kocjan.automatizer.domain.user.port;

import java.util.Optional;

import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.user.dto.UserDto;

public interface UserRepository {

	Optional<Error> findByEmail(String email);

	Optional<Error> findByUsername(String username);

	String saveUser(UserDto userToDto);

}
