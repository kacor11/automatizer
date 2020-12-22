package pl.kocjan.automatizer.domain.user;

import java.util.Optional;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.user.dto.CreateUserDto;
import pl.kocjan.automatizer.domain.user.dto.UserError;
import pl.kocjan.automatizer.domain.user.port.UserRepository;

@RequiredArgsConstructor
class UserCreator {
	
	private final UserMapper userMapper;
	private final UserRepository userRepository;

	Either<Error , Success> create(CreateUserDto createUserDto) {
		Optional<Error> hasValidationErrors = hasValidationErrors(createUserDto);
		
		return hasValidationErrors.isEmpty() ? createUser(createUserDto) :
			Either.left(hasValidationErrors.get());
		
	}

	private Either<Error, Success> createUser(CreateUserDto createUserDto) {
		User user = User.createUser(createUserDto);
		return saveUser(user);
	}
	
	private Either<Error, Success> saveUser(User user) {
		return Try.of(() -> save(user))
				.toEither(UserError.DATABASE_ERROR);
	}
	
	private Success save(User user) {
		userRepository.saveUser(userMapper.userToDto(user));
		return new Success("Host created successfully");
	}

	private Optional<Error> hasValidationErrors(CreateUserDto createUserDto) {
		if(userRepository.findByUsername(createUserDto.getUsername()).isPresent()) return Optional.of(UserError.USERNAME_ALREADY_EXISTS);
		if(userRepository.findByEmail(createUserDto.getEmail()).isPresent()) return Optional.of(UserError.EMAIL_ALREADY_EXISTS);
		return Optional.empty();
	}

}
