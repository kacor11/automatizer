package pl.kocjan.automatizer.user;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.adapter.repository.inmemory.InMemoryUserRepository;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.user.UserFacade;
import pl.kocjan.automatizer.domain.user.dto.CreateUserDto;
import pl.kocjan.automatizer.domain.user.port.UserRepository;

public class UserActivatorTest {
	private UserRepository userRepository = new InMemoryUserRepository();
	private UserFacade userFacade = new UserFacade(userRepository);
	
	private final CreateUserDto VALID_CREATE_USER_DTO = 
			CreateUserDto.builder()
				.username("username")
				.password("password")
				.email("asd@asd.com")
				.build();
	
	@Before
	public void resetHostList() {
		userRepository = new InMemoryUserRepository();
	}
	
	@Test
	public void shouldSuccesfulyActivateUser() {
		//given
		userFacade.create(VALID_CREATE_USER_DTO);
		String username = VALID_CREATE_USER_DTO.getUsername();
		
		//when
		Either<Error, Success> result = userFacade.activateUser(username);
		
		//then
		assertTrue(result.isRight());		
	}
}
