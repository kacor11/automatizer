package pl.kocjan.automatizer.domain.user;

import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.user.dto.UserDto;
import pl.kocjan.automatizer.domain.user.dto.UserError;
import pl.kocjan.automatizer.domain.user.port.UserRepository;

@RequiredArgsConstructor
public class UserActivator {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	Either<Error, Success> activate(String username) {
		return findUser(username)
				.flatMap(this::activateUser);
	}
	
	
	private Either<Error, UserDto> findUser(String username) {
		return Option.ofOptional(userRepository.findByUsername(username)).toEither(UserError.USER_NOT_FOUND);
	}
	
	private Either<Error, Success> activateUser(UserDto userDto) {
		User user = userMapper.dtoToUser(userDto);
		user.verifyUser();
		return Try.of(() -> persist(userMapper.userToDto(user)))
				.toEither(UserError.DATABASE_ERROR);
	}
	
	private Success persist(UserDto userDto) {
		userRepository.saveUser(userDto);
		return new Success("User successfully activated");
	}
}
