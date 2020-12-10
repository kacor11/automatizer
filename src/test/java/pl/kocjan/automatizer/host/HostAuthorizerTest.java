package pl.kocjan.automatizer.host;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.adapter.repository.inmemory.InMemoryHostRepository;
import pl.kocjan.automatizer.domain.common.dto.CommandRunnerError;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.host.dto.AuthorizeHostDto;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

public class HostAuthorizerTest {
	private HostRepository hostRepository = new InMemoryHostRepository();
	private HostFacade hostFacade = new HostFacade(hostRepository);
	
	@Before
	public void resetHostList() {
		hostRepository = new InMemoryHostRepository();
	}
	
	@Test
	public void shouldValidateHost() {
		//given
		CreateHostDto validCreateHostDto = TestHostFactory.validHostCreationDtoToAuthorize();
		AuthorizeHostDto validAuthorizeHostDto = TestHostFactory.validAuthorizationHostDto();
		String hostIp = validCreateHostDto.getIp();
		
		//when
		hostFacade.createHost(validCreateHostDto);
		Either<Error, Success> result = hostFacade.authorizeHost(validAuthorizeHostDto);
		
		//then
		assertEquals(result, Either.right(new Success()));
		assertTrue(hostFacade.readHost(hostIp).get().isAuthorized());
	}
	
	@Test
	public void shouldFailWithInvalidSshPassword() {
		//given
		CreateHostDto validCreateHostDto = TestHostFactory.validHostCreationDtoToAuthorize();
		AuthorizeHostDto invalidAuthorizationHostDto = TestHostFactory.invalidAuthorizationHostDto();
		String hostIp = validCreateHostDto.getIp();
		
		//when
		hostFacade.createHost(validCreateHostDto);
		Either<Error, Success> result = hostFacade.authorizeHost(invalidAuthorizationHostDto);
		
		//then
		assertEquals(result, Either.left(CommandRunnerError.INCORRECT_SSH_PASSWORD));
		assertFalse(hostFacade.readHost(hostIp).get().isAuthorized());
	}
}

