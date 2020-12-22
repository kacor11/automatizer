package pl.kocjan.automatizer.domain.user;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.user.dto.CreateUserDto;
import pl.kocjan.automatizer.domain.user.port.UserRepository;

public class UserFacade {
	
	private final UserCreator userCreator;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	public UserFacade(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.userMapper = new UserMapper();
		this.userCreator = new UserCreator(userMapper, userRepository);
	}
	
	Either<Error, Success> create (CreateUserDto createUserDto) {
		return userCreator.create(createUserDto);
	}
}
