package pl.kocjan.automatizer.domain.user;

import java.util.Optional;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.user.dto.CreateUserDto;
import pl.kocjan.automatizer.domain.user.dto.UserDto;
import pl.kocjan.automatizer.domain.user.port.UserRepository;

public class UserFacade {
	
	private final UserCreator userCreator;
	private final UserReader userReader;
	private final UserActivator userActivator;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	public UserFacade(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.userMapper = new UserMapper();
		this.userActivator = new UserActivator(userRepository, userMapper);
		this.userReader = new UserReader(userRepository);
		this.userCreator = new UserCreator(userMapper, userRepository);
	}
	
	public Either<Error, Success> create (CreateUserDto createUserDto) {
		return userCreator.create(createUserDto);
	}
	
	public Either<Error, UserDto> readUser (String username) {
		return userReader.readUser(username);
	}
	
	public Either<Error, Success> activateUser (String username) {
		return userActivator.activate(username);
	}
}
