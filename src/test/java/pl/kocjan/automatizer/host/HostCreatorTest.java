package pl.kocjan.automatizer.host;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.adapter.repository.inmemory.InMemoryHostRepository;
import pl.kocjan.automatizer.domain.common.vavr.Success;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.host.HostFacade;
import pl.kocjan.automatizer.domain.host.dto.CreateHostDto;
import pl.kocjan.automatizer.domain.host.dto.HostError;
import pl.kocjan.automatizer.domain.host.port.HostRepository;

public class HostCreatorTest {
	
	private HostRepository hostRepository = new InMemoryHostRepository();
	private HostFacade hostFacade = new HostFacade(hostRepository);
	
	@Before
	public void resetHostList() {
		hostRepository = new InMemoryHostRepository();
	}
	
	@Test
	public void shouldCreateHost() {
		//given
		CreateHostDto validCreateHostDto = TestHostFactory.validHostCreationDto();
		String hostIp = validCreateHostDto.getIp();
		
		//when
		hostFacade.createHost(validCreateHostDto);
		
		//then
		assertTrue(hostFacade.readHost(hostIp).isPresent());		
	}
	
	
	@Test
	public void shouldCreateTwoDifferenHosts() {
		//given
		CreateHostDto validCreateHostDto = TestHostFactory.validHostCreationDto();
		CreateHostDto validCreateHostDto2 = TestHostFactory.validHostCreationDto();
		String hostIp = validCreateHostDto.getIp();
		String hostIp2 = validCreateHostDto2.getIp();
		
		//when
		hostFacade.createHost(validCreateHostDto);
		hostFacade.createHost(validCreateHostDto2);
		
		//then
		assertTrue(hostFacade.readHost(hostIp).isPresent());
		assertTrue(hostFacade.readHost(hostIp2).isPresent());
		
	}
	
	@Test 
	public void shouldReturnHostAlreadyExist() {
		//given
		CreateHostDto validCreateHostDto = TestHostFactory.validHostCreationDto();
		
		//when		
		Either<Error, Success> result1 = hostFacade.createHost(validCreateHostDto);
		Either<Error, Success> result2 = hostFacade.createHost(validCreateHostDto);
		
		//then
		assertTrue(result1.isRight());
		assertTrue(result2.isLeft());
		assertEquals(result2.getLeft(), HostError.HOST_ALREADY_EXISTS);
		
	}
}
