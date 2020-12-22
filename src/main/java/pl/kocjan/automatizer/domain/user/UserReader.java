package pl.kocjan.automatizer.domain.user;

import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.user.dto.UserDto;
import pl.kocjan.automatizer.domain.user.dto.UserError;
import pl.kocjan.automatizer.domain.user.port.UserRepository;

@RequiredArgsConstructor
class UserReader {
	
	private final UserRepository userRepository;
	
	Either<Error, UserDto> readUser(String username) {
		return Option.ofOptional(userRepository.findByUsername(username)).toEither(UserError.USER_NOT_FOUND);
	}
}
