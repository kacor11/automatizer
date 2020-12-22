package pl.kocjan.automatizer.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.adapter.repository.inmemory.InMemoryUserRepository;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.user.UserFacade;
import pl.kocjan.automatizer.domain.user.dto.CreateUserDto;
import pl.kocjan.automatizer.domain.user.dto.UserError;
import pl.kocjan.automatizer.domain.user.port.UserRepository;


public class UserCreatorTest {
	private UserRepository userRepository = new InMemoryUserRepository();
	private UserFacade userFacade = new UserFacade(userRepository);
	
	private final CreateUserDto VALID_CREATE_USER_DTO = 
			CreateUserDto.builder()
				.username("username")
				.password("password")
				.email("asd@asd.com")
				.build();
	
	private final CreateUserDto VALID_CREATE_USER_DTO2 = 
			CreateUserDto.builder()
				.username("username")
				.password("password")
				.email("zxcv@asd.com")
				.build();
	private final CreateUserDto VALID_CREATE_USER_DTO3 = 
			CreateUserDto.builder()
				.username("username1")
				.password("password")
				.email("asd@asd.com")
				.build();
	
	@Before
	public void resetHostList() {
		userRepository = new InMemoryUserRepository();
	}
	
	@Test
	public void shouldCreateUserGivenValidDto() {
		
		//when
		Either<Error, Success> result = userFacade.create(VALID_CREATE_USER_DTO);
		String username = VALID_CREATE_USER_DTO.getUsername();
		
		//then
		assertTrue(result.isRight());
		assertTrue(userRepository.findByUsername(username).isPresent());
	}
	
	@Test
	public void shouldFailWhenCreatingWithIdenticalUsername() {
		
		//when
		Either<Error, Success> result = userFacade.create(VALID_CREATE_USER_DTO);
		Either<Error, Success> result2 = userFacade.create(VALID_CREATE_USER_DTO2);
		
		//then
		assertTrue(result.isRight());
		assertTrue(result2.isLeft());
		assertEquals(Either.left(UserError.USERNAME_ALREADY_EXISTS), result2);
	}

	@Test
	public void shouldFailWhenCreatingWithIdenticalEmail() {
		//when
		Either<Error, Success> result = userFacade.create(VALID_CREATE_USER_DTO);
		Either<Error, Success> result2 = userFacade.create(VALID_CREATE_USER_DTO3);
		
		//then
		assertTrue(result.isRight());
		assertTrue(result2.isLeft());
		assertEquals(Either.left(UserError.EMAIL_ALREADY_EXISTS), result2);
	}

}
